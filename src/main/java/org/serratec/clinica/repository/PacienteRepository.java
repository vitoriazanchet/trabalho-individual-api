package org.serratec.clinica.repository;

import java.util.List;

import org.serratec.clinica.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    List<Paciente> findByNomeContainingIgnoreCase(String nome);

}
