package org.serratec.clinica.controller;

import java.util.List;
import java.time.LocalDateTime;

import org.serratec.clinica.domain.Consulta;
import org.serratec.clinica.repository.ConsultaRepository;
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
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> consultas = consultaRepository.findAll();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id){
        return consultaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Consulta>> buscarPorData(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        List<Consulta> consultas = consultaRepository.findByDataBetween(inicio, fim);
        return ResponseEntity.ok(consultas);
    }

    @PostMapping
    public ResponseEntity<Consulta> cadastrar(@Valid @RequestBody Consulta consulta) {
        Consulta salvo = consultaRepository.save(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
        if (!consultaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consulta.setId(id);
        Consulta atualizado = consultaRepository.save(consulta);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!consultaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consultaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
