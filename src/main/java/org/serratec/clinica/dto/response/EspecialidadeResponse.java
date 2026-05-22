package org.serratec.clinica.dto.response;

public class EspecialidadeResponse {

    private Long id;
    private String especialidade;
    
    public EspecialidadeResponse() {
    }

    public EspecialidadeResponse(Long id, String especialidade) {
        this.id = id;
        this.especialidade = especialidade;
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
    
}
