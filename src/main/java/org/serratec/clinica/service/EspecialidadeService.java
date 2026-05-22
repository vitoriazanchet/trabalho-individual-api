package org.serratec.clinica.service;
 
import java.util.List;
import java.util.stream.Collectors;
 
import org.serratec.clinica.domain.Especialidade;
import org.serratec.clinica.dto.request.EspecialidadeRequest;
import org.serratec.clinica.dto.response.EspecialidadeResponse;
import org.serratec.clinica.exception.ResourceNotFoundException;
import org.serratec.clinica.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class EspecialidadeService {
 
    @Autowired
    private EspecialidadeRepository especialidadeRepository;
 
    public List<EspecialidadeResponse> listar() {
        return especialidadeRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
 
    public EspecialidadeResponse buscarPorId(Long id) {
        return toResponse(especialidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Especialidade com ID " + id + " não encontrada")));
    }
 
    public List<EspecialidadeResponse> buscarPorNome(String nome) {
        return especialidadeRepository.findByEspecialidadeContainingIgnoreCase(nome)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }
 
    public EspecialidadeResponse cadastrar(EspecialidadeRequest request) {
        Especialidade e = new Especialidade();
        e.setEspecialidade(request.getEspecialidade());
        return toResponse(especialidadeRepository.save(e));
    }
 
    public EspecialidadeResponse atualizar(Long id, EspecialidadeRequest request) {
        especialidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Especialidade com ID " + id + " não encontrada"));
        Especialidade e = new Especialidade();
        e.setId(id);
        e.setEspecialidade(request.getEspecialidade());
        return toResponse(especialidadeRepository.save(e));
    }
 
    public void apagar(Long id) {
        if (!especialidadeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Especialidade com ID " + id + " não encontrada");
        }
        especialidadeRepository.deleteById(id);
    }
 
    private EspecialidadeResponse toResponse(Especialidade e) {
        return new EspecialidadeResponse(e.getId(), e.getEspecialidade());
    }
}