package org.serratec.clinica.dto.response;

import java.time.LocalDateTime;
import org.serratec.clinica.domain.StatusConsulta;

public class ConsultaResponse {

    private Long id;
    private LocalDateTime data;
    private StatusConsulta status;
    private String nomePaciente;
    private String nomeMedico;
    
    public ConsultaResponse() {
    }

    public ConsultaResponse(Long id, LocalDateTime data, StatusConsulta status, String nomePaciente, String nomeMedico) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.nomePaciente = nomePaciente;
        this.nomeMedico = nomeMedico;
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

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

}