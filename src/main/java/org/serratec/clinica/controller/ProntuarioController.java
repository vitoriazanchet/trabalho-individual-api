package org.serratec.clinica.controller;

import java.util.List;

import org.serratec.clinica.dto.request.ProntuarioRequest;
import org.serratec.clinica.dto.response.ProntuarioResponse;
import org.serratec.clinica.service.ProntuarioService;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioSercive;

    @GetMapping
    public ResponseEntity<List<ProntuarioResponse>> listar() {
        return ResponseEntity.ok(prontuarioSercive.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(prontuarioSercive.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProntuarioResponse> cadastrar(@Valid @RequestBody ProntuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prontuarioSercive.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ProntuarioRequest request) {
        return ResponseEntity.ok(prontuarioSercive.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        prontuarioSercive.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
