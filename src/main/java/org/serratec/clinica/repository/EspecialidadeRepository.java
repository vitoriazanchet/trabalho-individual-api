package org.serratec.clinica.repository;

import java.util.List;

import org.serratec.clinica.domain.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    List<Especialidade> findByEspecialidadeContainingIgnoreCase(String especialidade);
    
}
