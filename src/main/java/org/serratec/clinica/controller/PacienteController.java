package org.serratec.clinica.controller;

import java.util.List;

import org.serratec.clinica.dto.request.PacienteRequest;
import org.serratec.clinica.dto.response.PacienteResponse;
import org.serratec.clinica.service.PacienteService;
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
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listar() {
        return ResponseEntity.ok(pacienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PacienteResponse>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(pacienteService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> cadastrar(@Valid @RequestBody PacienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequest request) {
        return ResponseEntity.ok(pacienteService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        pacienteService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
