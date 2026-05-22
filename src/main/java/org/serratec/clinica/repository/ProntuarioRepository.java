package org.serratec.clinica.repository;

import java.util.Optional;

import org.serratec.clinica.domain.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    Optional<Prontuario> findByPacienteId(Long pacienteId);
}
