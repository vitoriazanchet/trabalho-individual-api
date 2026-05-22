package org.serratec.clinica.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonBackReference
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    @JsonBackReference
    private Medico medico;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime data, StatusConsulta status, Paciente paciente, Medico medico) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

}
