package org.serratec.clinica.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(name = "data_nascimento")
	private LocalDate dataNascimento;

    private String email;

    private String telefone;

    @OneToOne(mappedBy = "paciente")
    private Prontuario prontuario;
    
    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference
    private List<Consulta> consulta;

    public Paciente() {
        super();
    }
    
    public Paciente(Long id, String nome, String cpf, LocalDate dataNascimento, String telefone, String email) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Prontuario getProntuario() { 
        return prontuario; 
    }

    public void setProntuario(Prontuario prontuario) { 
        this.prontuario = prontuario; 
    }

    public List<Consulta> getConsulta() { 
        return consulta; 
    }
    
    public void setConsulta(List<Consulta> consulta) { 
        this.consulta = consulta; 
    }

}