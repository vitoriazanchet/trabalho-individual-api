package org.serratec.clinica.service;

import java.util.stream.Collectors;
import java.util.List;

import org.serratec.clinica.domain.Paciente;
import org.serratec.clinica.dto.request.PacienteRequest;
import org.serratec.clinica.dto.response.PacienteResponse;
import org.serratec.clinica.exception.DuplicateEntryException;
import org.serratec.clinica.exception.ResourceNotFoundException;
import org.serratec.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
 
    public List<PacienteResponse> listar() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
 
    public PacienteResponse buscarPorId(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Paciente com ID " + id + " não encontrado"));
        return toResponse(paciente);
    }
 
    public List<PacienteResponse> buscarPorNome(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
 
    public PacienteResponse cadastrar(PacienteRequest request) {
        pacienteRepository.findByCpf(request.getCpf()).ifPresent(p -> {
            throw new DuplicateEntryException("CPF " + request.getCpf() + " já cadastrado");
        });
 
        Paciente paciente = toEntity(request);
        Paciente salvo = pacienteRepository.save(paciente);
        return toResponse(salvo);
    }
 
    public PacienteResponse atualizar(Long id, PacienteRequest request) {
        Paciente existente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Paciente com ID " + id + " não encontrado"));
        pacienteRepository.findByCpf(request.getCpf()).ifPresent(p -> {
            if (!p.getId().equals(id)) {
                throw new DuplicateEntryException("CPF " + request.getCpf() + " já pertence a outro paciente");
            }
        });
        
        existente.setNome(request.getNome());
        existente.setCpf(request.getCpf());
        return toResponse(pacienteRepository.save(existente));
    }
 
    public void apagar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente com ID " + id + " não encontrado");
        }
        pacienteRepository.deleteById(id);
    }
 
    private Paciente toEntity(PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setCpf(request.getCpf());
        p.setDataNascimento(request.getDataNascimento());
        p.setEmail(request.getEmail());
        p.setTelefone(request.getTelefone());
        return p;
    }
 
    private PacienteResponse toResponse(Paciente p) {
        return new PacienteResponse(
            p.getId(),
            p.getNome(),
            p.getCpf(),
            p.getDataNascimento(),
            p.getEmail(),
            p.getTelefone()
        );
    }
}
