package org.serratec.clinica.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private int status;
    private String mensagem;
    private LocalDateTime timestamp;
    private List<String> erros;

    public ErrorResponse(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(int status, String mensagem, List<String> erros) {
        this(status, mensagem);
        this.erros = erros;
    }

    public int getStatus() { 
        return status; 
    }
    
    public String getMensagem() { 
        return mensagem; 
    }

    public LocalDateTime getTimestamp() { 
        return timestamp; 
    }

    public List<String> getErros() { 
        return erros; 
    }

}
