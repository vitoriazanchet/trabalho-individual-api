package org.serratec.clinica.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crm;

    @ManyToMany
    @JoinTable(
        name = "medico_especialidade", 
        joinColumns = @JoinColumn(name = "medico_id"), 
        inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    @JsonIgnore
    private List<Especialidade> especialidade;

    @OneToMany(mappedBy = "medico")
    @JsonManagedReference
    @JsonIgnore
    private List<Consulta> consulta;

    public Medico() {
        super();
    }

    public Medico(Long id, String nome, String crm, List<Especialidade> especialidade, List<Consulta> consulta) {
        super();
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.consulta = consulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<Especialidade> getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(List<Especialidade> especialidade) {
        this.especialidade = especialidade;
    }

    public List<Consulta> getConsulta() { 
        return consulta;
    }

    public void setConsulta(List<Consulta> consulta) { 
        this.consulta = consulta;
    }

}
