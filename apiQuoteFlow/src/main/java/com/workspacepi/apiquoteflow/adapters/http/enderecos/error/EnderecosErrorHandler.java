//package com.workspacepi.apiquoteflow.adapters.http.enderecos.error;
//
//import com.workspacepi.apiquoteflow.application.enderecos.exceptions.EnderecoNaoEncontradoException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.UUID;
//
//@RestControllerAdvice
//public class EnderecosErrorHandler {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecosErrorHandler.class);
//
//    @ExceptionHandler(EnderecoNaoEncontradoException.class)
//    public ResponseEntity<Object> handleEnderecoNaoEncontradoException(EnderecoNaoEncontradoException e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    // Método genérico para outras exceções
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<EnderecosErrorResponse> handleException(Exception e) {
//        return buildResponse(null, HttpStatus.BAD_REQUEST, "Erro desconhecido", e);
//    }
//
//    // Método auxiliar para construir a resposta
//    private ResponseEntity<EnderecosErrorResponse> buildResponse(UUID id, HttpStatus status, String message, Throwable throwable) {
//        EnderecosErrorResponse response = new EnderecosErrorResponse(id, message);
//        String fullMessage = "[" + status.value() + "] [" + throwable.getClass().getSimpleName() + "] " + message;
//
//        if (status.is5xxServerError()) {
//            LOGGER.error(fullMessage, throwable);
//        } else {
//            LOGGER.warn(fullMessage);
//        }
//
//        return ResponseEntity.status(status).body(response);
//    }
//}
