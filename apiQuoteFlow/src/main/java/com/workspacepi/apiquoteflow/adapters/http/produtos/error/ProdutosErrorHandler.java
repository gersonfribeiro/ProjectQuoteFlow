package com.workspacepi.apiquoteflow.adapters.http.produtos.error;

import com.workspacepi.apiquoteflow.application.produtos.exceptions.ProdutoNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class ProdutosErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutosErrorHandler.class);

    // Lida com a empresaNaoEncontradaException
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<Object> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Método genérico para outras exceções
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProdutosErrorResponse> handleException(Exception e) {
        return buildResponse(null, HttpStatus.BAD_REQUEST, "Erro desconhecido", e);
    }

    // Método auxiliar para construir a resposta
    private ResponseEntity<ProdutosErrorResponse> buildResponse(UUID id, HttpStatus status, String message, Throwable throwable) {
        ProdutosErrorResponse response = new ProdutosErrorResponse(id, message);
        String fullMessage = "[" + status.value() + "] [" + throwable.getClass().getSimpleName() + "] " + message;

        if (status.is5xxServerError()) {
            LOGGER.error(fullMessage, throwable);
        } else {
            LOGGER.warn(fullMessage);
        }

        return ResponseEntity.status(status).body(response);
    }
}
