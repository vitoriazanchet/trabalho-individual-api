package org.serratec.clinica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProntuarioRequest {

    @NotBlank(message = "As observações são obrigatórias")
    private String observacoes;

    @NotBlank(message = "O hitórico deve ser preenchido")
    private String historico;

    @NotNull(message = "O Id do paciente é obrigatório")
    private Long idPaciente;

    public ProntuarioRequest() {
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
    
}
