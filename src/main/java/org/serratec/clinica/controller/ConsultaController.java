package org.serratec.clinica.controller;

import java.util.List;
import java.time.LocalDateTime;

import org.serratec.clinica.dto.request.ConsultaRequest;
import org.serratec.clinica.dto.response.ConsultaResponse;
import org.serratec.clinica.service.ConsultaService;
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
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<ConsultaResponse>> listar() {
        return ResponseEntity.ok(consultaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ConsultaResponse>> buscarPorData(@RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fim){
        return ResponseEntity.ok(consultaService.buscarPorData(inicio, fim));
    }

    @PostMapping
    public ResponseEntity<ConsultaResponse> cadastrar(@Valid @RequestBody ConsultaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ConsultaRequest request) {
        return ResponseEntity.ok(consultaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        consultaService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
