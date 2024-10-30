package com.workspacepi.apiquoteflow.application.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAutenticationDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("senha")
    private String senha;

    public Usuarios toUsuario() {
        return new Usuarios(email, senha);
    }

    public UsuarioAutenticationDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}
