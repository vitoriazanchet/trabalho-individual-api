package org.serratec.clinica.dto.response;

public class ProntuarioResponse {

    private Long id;
    private String observacoes;
    private String historico;
    private String nomePaciente;

    public ProntuarioResponse() {
    }

    public ProntuarioResponse(Long id, String observacoes, String historico, String nomePaciente) {
        this.id = id;
        this.observacoes = observacoes;
        this.historico = historico;
        this.nomePaciente = nomePaciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

}
