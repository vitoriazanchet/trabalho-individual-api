package org.serratec.clinica.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "especialidade")
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade")
    private Long id;

    @Column(nullable = false)
    private String especialidade;

    @ManyToMany(mappedBy = "especialidade")
    @JsonIgnore
    private List<Medico> medico;

    public Especialidade() {
        super();
    }

    public Especialidade(Long id, String especialidade, List<Medico> medico) {
        super();
        this.id = id;
        this.especialidade = especialidade;
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<Medico> getMedicos() {
        return medico;
    }

    public void setMedicos(List<Medico> medico) {
        this.medico = medico;
    }

}
