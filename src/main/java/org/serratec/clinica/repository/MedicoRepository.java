package org.serratec.clinica.repository;

import java.util.List;

import org.serratec.clinica.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findByNomeContainingIgnoreCase(String nome);

}