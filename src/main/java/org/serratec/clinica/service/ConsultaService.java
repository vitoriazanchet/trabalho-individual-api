package org.serratec.clinica.service;

import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.serratec.clinica.domain.Consulta;
import org.serratec.clinica.domain.Medico;
import org.serratec.clinica.domain.Paciente;
import org.serratec.clinica.dto.request.ConsultaRequest;
import org.serratec.clinica.dto.response.ConsultaResponse;
import org.serratec.clinica.exception.DuplicateEntryException;
import org.serratec.clinica.exception.ResourceNotFoundException;
import org.serratec.clinica.repository.ConsultaRepository;
import org.serratec.clinica.repository.MedicoRepository;
import org.serratec.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class ConsultaService {
 
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;
 
    @Autowired
    private MedicoRepository medicoRepository;
 
    public List<ConsultaResponse> listar() {
        return consultaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
 
    public ConsultaResponse buscarPorId(Long id) {
        return toResponse(consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Consulta com ID " + id + " não encontrada")));
    }
 
    public List<ConsultaResponse> buscarPorData(LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findByDataBetween(inicio, fim).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
 
    public ConsultaResponse cadastrar(ConsultaRequest request) {
        Paciente paciente = pacienteRepository.findById(request.getIdPaciente())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Paciente com ID " + request.getIdPaciente() + " não encontrado"));

        Medico medico = medicoRepository.findById(request.getIdMedico())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Médico com ID " + request.getIdMedico() + " não encontrado"));

        if (consultaRepository.existsByMedicoIdAndData(request.getIdMedico(), request.getData())) {
            throw new DuplicateEntryException("O médico já possui uma consulta agendada para este horário.");
        }

        if (consultaRepository.existsByPacienteIdAndData(request.getIdPaciente(), request.getData())) {
            throw new DuplicateEntryException("O paciente já possui uma consulta agendada para este horário.");
        }

        Consulta consulta = new Consulta();
        consulta.setData(request.getData());
        consulta.setStatus(request.getStatus());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        return toResponse(consultaRepository.save(consulta));
    }
 
    public ConsultaResponse atualizar(Long id, ConsultaRequest request) {
        Consulta existente = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta com ID " + id + " não encontrada"));

        Paciente paciente = pacienteRepository.findById(request.getIdPaciente())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        Medico medico = medicoRepository.findById(request.getIdMedico())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
                
        existente.setData(request.getData());
        existente.setStatus(request.getStatus());
        existente.setPaciente(paciente);
        existente.setMedico(medico);

        return toResponse(consultaRepository.save(existente));
    }
 
    public void apagar(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta com ID " + id + " não encontrada");
        }
        consultaRepository.deleteById(id);
    }
 
    private ConsultaResponse toResponse(Consulta c) {
        String nomePaciente = c.getPaciente() != null ? c.getPaciente().getNome() : null;
        String nomeMedico = c.getMedico() != null ? c.getMedico().getNome() : null;
        return new ConsultaResponse(c.getId(), c.getData(), c.getStatus(), nomePaciente, nomeMedico); 
    }
}