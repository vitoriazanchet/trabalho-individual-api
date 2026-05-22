package org.serratec.clinica.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.serratec.clinica.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);
    boolean existsByMedicoIdAndData(Long medicoId, LocalDateTime data);
    boolean existsByPacienteIdAndData(Long pacienteId, LocalDateTime data);

}
