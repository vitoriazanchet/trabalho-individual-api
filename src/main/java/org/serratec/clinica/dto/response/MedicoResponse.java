package org.serratec.clinica.dto.response;

import java.util.List;

public class MedicoResponse {

    private Long id;
    private String nome;
    private String crm;
    private List<String> especialidade;

    public MedicoResponse() {
    }

    public MedicoResponse(Long id, String nome, String crm, List<String> especialidade) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
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

    public List<String> getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(List<String> especialidade) {
        this.especialidade = especialidade;
    }

}
