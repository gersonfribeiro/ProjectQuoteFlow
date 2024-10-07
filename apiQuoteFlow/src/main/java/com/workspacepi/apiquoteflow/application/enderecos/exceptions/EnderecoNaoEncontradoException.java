package com.workspacepi.apiquoteflow.application.enderecos.exceptions;

import java.util.UUID;

// Exceção específica para quando um endereco não for encontrado
public class EnderecoNaoEncontradoException extends EnderecosExceptions {

    public EnderecoNaoEncontradoException(UUID id_endereco) {
        super("Endereco " + id_endereco + " não encontrado", id_endereco);
    }
}
