package org.serratec.clinica.controller;

import java.util.List;

import org.serratec.clinica.dto.request.EspecialidadeRequest;
import org.serratec.clinica.dto.response.EspecialidadeResponse;
import org.serratec.clinica.service.EspecialidadeService;
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
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping
    public ResponseEntity<List<EspecialidadeResponse>> listar() {
        return ResponseEntity.ok(especialidadeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(especialidadeService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EspecialidadeResponse>> buscarPorNome(@RequestParam String especialidade) {
        return ResponseEntity.ok(especialidadeService.buscarPorNome(especialidade));
    }

    @PostMapping
    public ResponseEntity<EspecialidadeResponse> cadastrar(@Valid @RequestBody EspecialidadeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadeService.cadastrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadeResponse> atualizar(@PathVariable Long id, @Valid @RequestBody EspecialidadeRequest request) {
        return ResponseEntity.ok(especialidadeService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        especialidadeService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
