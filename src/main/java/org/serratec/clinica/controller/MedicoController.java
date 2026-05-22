package org.serratec.clinica.controller;

import java.util.List;

import org.serratec.clinica.domain.Medico;
import org.serratec.clinica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        List<Medico> medicos = medicoRepository.findAll();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id){
        return medicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Medico>> buscarPorNome(@RequestParam String nome) {
        List<Medico> medicos = medicoRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity<Medico> cadastrar(@Valid @RequestBody Medico medico) {
        Medico salvo = medicoRepository.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico) {
        if (!medicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        medico.setId(id);
        Medico atualizado = medicoRepository.save(medico);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!medicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        medicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}