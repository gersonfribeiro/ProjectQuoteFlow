package com.workspacepi.apiquoteflow.application.usuarios.exceptions;

import com.workspacepi.apiquoteflow.domain.usuarios.Permissoes;
import lombok.Getter;

@Getter
public class UsuarioPermissaoNegadaException extends RuntimeException {

    private final Permissoes permissao;

    public UsuarioPermissaoNegadaException(Permissoes permissao) {
        super("Permissão de acesso negada! Sua permissão é de nível de: " + permissao.name());
        this.permissao = permissao;
    }
}
