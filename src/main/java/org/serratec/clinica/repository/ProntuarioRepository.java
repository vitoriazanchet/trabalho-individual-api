package org.serratec.clinica.repository;

import org.serratec.clinica.domain.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

}
