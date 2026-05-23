package org.serratec.clinica.controller;

import java.util.List;

import org.serratec.clinica.dto.request.MedicoRequest;
import org.serratec.clinica.dto.response.MedicoResponse;
import org.serratec.clinica.service.MedicoService;
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
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> listar() {
        return ResponseEntity.ok(medicoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MedicoResponse>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(medicoService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<MedicoResponse> cadastrar(@Valid @RequestBody MedicoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualizar(@PathVariable Long id, @RequestBody MedicoRequest request) {
        return ResponseEntity.ok(medicoService.atualizar(id, request));
    }

    @PutMapping("/{id}/especialidades")
    public ResponseEntity<MedicoResponse> associarEspecialidades(@PathVariable Long id, @Valid @RequestBody List<Long> especialidadeIds) {
        return ResponseEntity.ok(medicoService.associarEspecialidades(id, especialidadeIds));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        medicoService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}