package org.serratec.clinica.controller;

import java.util.List;

import org.serratec.clinica.domain.Especialidade;
import org.serratec.clinica.repository.EspecialidadeRepository;
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
    private EspecialidadeRepository especialidadeRepository;

    @GetMapping
    public ResponseEntity<List<Especialidade>> listar() {
        List<Especialidade> especialidades = especialidadeRepository.findAll();
        return ResponseEntity.ok(especialidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscarPorId(@PathVariable Long id){
        return especialidadeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Especialidade>> buscarPorNome(
            @RequestParam String especialidade) {
        List<Especialidade> lista = especialidadeRepository
                .findByEspecialidadeContainingIgnoreCase(especialidade);
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Especialidade> cadastrar(@Valid @RequestBody Especialidade especialidade) {
        Especialidade salvo = especialidadeRepository.save(especialidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Long id, @RequestBody Especialidade especialidade) {
        if (!especialidadeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        especialidade.setId(id);
        Especialidade atualizado = especialidadeRepository.save(especialidade);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!especialidadeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        especialidadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
