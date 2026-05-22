package org.serratec.clinica.service;
  
import java.util.List;
import java.util.stream.Collectors;

import org.serratec.clinica.domain.Especialidade;
import org.serratec.clinica.domain.Medico;
import org.serratec.clinica.dto.request.MedicoRequest;
import org.serratec.clinica.dto.response.MedicoResponse;
import org.serratec.clinica.exception.DuplicateEntryException;
import org.serratec.clinica.exception.ResourceNotFoundException;
import org.serratec.clinica.repository.EspecialidadeRepository;
import org.serratec.clinica.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
 
    @Autowired
    private MedicoRepository medicoRepository;
 
    @Autowired
    private EspecialidadeRepository especialidadeRepository;
 
    public List<MedicoResponse> listar() {
        return medicoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
 
    public MedicoResponse buscarPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Médico com ID " + id + " não encontrado"));
        return toResponse(medico);
    }
 
    public List<MedicoResponse> buscarPorNome(String nome) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
 
    public MedicoResponse cadastrar(MedicoRequest request) {
        medicoRepository.findByCrm(request.getCrm()).ifPresent(m -> {
            throw new DuplicateEntryException("CRM " + request.getCrm() + " já cadastrado");
        });
        Medico medico = toEntity(request);
        return toResponse(medicoRepository.save(medico));
    }
 
    public MedicoResponse atualizar(Long id, MedicoRequest request) {
        Medico existente = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Médico com ID " + id + " não encontrado"));
 
        medicoRepository.findByCrm(request.getCrm()).ifPresent(m -> {
            if (!m.getId().equals(id)) {
                throw new DuplicateEntryException("CRM " + request.getCrm() + " já pertence a outro médico");
            }
        });
 
        existente.setNome(request.getNome());
        existente.setCrm(request.getCrm());
        return toResponse(medicoRepository.save(existente));
}
 
    public void apagar(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Médico com ID " + id + " não encontrado");
        }
        medicoRepository.deleteById(id);
    }
    public MedicoResponse associarEspecialidades(Long medicoId, List<Long> especialidadeIds) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Médico com ID " + medicoId + " não encontrado"));
 
        List<Especialidade> especialidades = especialidadeIds.stream()
                .map(eId -> especialidadeRepository.findById(eId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                            "Especialidade com ID " + eId + " não encontrada")))
                .collect(Collectors.toList());
 
        medico.setEspecialidade(especialidades);
        return toResponse(medicoRepository.save(medico));
    }
 
    private Medico toEntity(MedicoRequest request) {
        Medico m = new Medico();
        m.setNome(request.getNome());
        m.setCrm(request.getCrm());
        return m;
    }
 
    private MedicoResponse toResponse(Medico m) {
    List<String> nomes = m.getEspecialidade() == null ? List.of() :
        m.getEspecialidade().stream()
            .map(Especialidade::getEspecialidade)
            .collect(Collectors.toList());
        return new MedicoResponse(m.getId(), m.getNome(), m.getCrm(), nomes);
    }
}
