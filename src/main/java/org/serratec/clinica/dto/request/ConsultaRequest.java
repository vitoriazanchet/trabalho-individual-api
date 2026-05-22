package org.serratec.clinica.dto.request;

import java.time.LocalDateTime;

import org.serratec.clinica.domain.StatusConsulta;

import jakarta.validation.constraints.NotNull;

public class ConsultaRequest {

    @NotNull(message = "Data da consulta é obrigatória")
    private LocalDateTime data;

    @NotNull(message = "Status é obrigatório")
    private StatusConsulta status;

    @NotNull(message = "O Id do paciente é obrigatório")
    private Long idPaciente;

    @NotNull(message = "O Id do médico é obrigatório")
    private Long idMedico;

    public ConsultaRequest() {
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

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }
    
}
