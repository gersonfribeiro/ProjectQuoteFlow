package com.workspacepi.apiquoteflow.application.usuarios;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioAuthenticationDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("senha")
    private String senha;

}
