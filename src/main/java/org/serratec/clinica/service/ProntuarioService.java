package org.serratec.clinica.service;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.clinica.domain.Paciente;
import org.serratec.clinica.domain.Prontuario;
import org.serratec.clinica.dto.request.ProntuarioRequest;
import org.serratec.clinica.dto.response.ProntuarioResponse;
import org.serratec.clinica.exception.ResourceNotFoundException;
import org.serratec.clinica.repository.PacienteRepository;
import org.serratec.clinica.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<ProntuarioResponse> listar() {
        return prontuarioRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProntuarioResponse buscarPorId(Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Prontuário com ID " + id + " não encontrado"));
        return toResponse(prontuario);
    }

    public ProntuarioResponse cadastrar(ProntuarioRequest request) {
        Paciente paciente = pacienteRepository.findById(request.getIdPaciente())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Paciente com ID " + request.getIdPaciente() + " não encontrado"));
        Prontuario prontuario = new Prontuario();
        prontuario.setObservacoes(request.getObservacoes());
        prontuario.setHistorico(request.getHistorico());
        prontuario.setPaciente(paciente);

        return toResponse(prontuarioRepository.save(prontuario));
    }

    public ProntuarioResponse atualizar(Long id, ProntuarioRequest request) {
        Prontuario existente = prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Prontuário com ID " + id + " não encontrado"));
        Paciente paciente = pacienteRepository.findById(request.getIdPaciente())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Paciente com ID " + request.getIdPaciente() + " não encontrado"));
        existente.setObservacoes(request.getObservacoes());
        existente.setHistorico(request.getHistorico());
        existente.setPaciente(paciente);

        return toResponse(prontuarioRepository.save(existente));
    }

    public void apagar(Long id) {
        if (!prontuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "Prontuário com ID " + id + " não encontrado");
        }
        prontuarioRepository.deleteById(id);
    }

    private ProntuarioResponse toResponse(Prontuario p) {
        String nomePaciente = p.getPaciente() != null ? p.getPaciente().getNome() : null;
        return new ProntuarioResponse(
            p.getId(),
            p.getObservacoes(),
            p.getHistorico(),
            nomePaciente
        );
    }
}