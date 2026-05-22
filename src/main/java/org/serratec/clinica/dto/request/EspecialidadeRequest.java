package org.serratec.clinica.dto.request;

import jakarta.validation.constraints.NotBlank;

public class EspecialidadeRequest {

    @NotBlank(message = "A especialidade é obrigatória")
    private String especialidade;

    public EspecialidadeRequest() {
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
}
