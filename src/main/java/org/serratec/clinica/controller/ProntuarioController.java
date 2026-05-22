package org.serratec.clinica.controller;

import java.util.List;

import org.serratec.clinica.domain.Prontuario;
import org.serratec.clinica.repository.ProntuarioRepository;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @GetMapping
    public ResponseEntity<List<Prontuario>> listar() {
        List<Prontuario> prontuarios = prontuarioRepository.findAll();
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> buscarPorId(@PathVariable Long id){
        return prontuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prontuario> inserir(@RequestBody Prontuario prontuario) {
        Prontuario salvo = prontuarioRepository.save(prontuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prontuario> atualizar(@PathVariable Long id, @RequestBody Prontuario prontuario) {
        if (!prontuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        prontuario.setId(id);
        Prontuario atualizado = prontuarioRepository.save(prontuario);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!prontuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        prontuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
