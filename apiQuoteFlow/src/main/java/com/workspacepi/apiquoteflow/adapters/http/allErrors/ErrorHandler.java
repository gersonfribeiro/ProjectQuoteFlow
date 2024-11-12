package com.workspacepi.apiquoteflow.adapters.http.allErrors;

import com.workspacepi.apiquoteflow.application.empresas.exceptions.*;
import com.workspacepi.apiquoteflow.application.produtos.exceptions.ProdutoNaoEncontradoException;
import com.workspacepi.apiquoteflow.application.produtos.exceptions.ProdutoSkuCadastradoException;
import com.workspacepi.apiquoteflow.application.produtos.exceptions.ProdutoSkuNaoEncontradoException;
import com.workspacepi.apiquoteflow.application.usuarios.exceptions.UsuarioEmailCadastradoException;
import com.workspacepi.apiquoteflow.application.usuarios.exceptions.UsuarioNaoAutenticadoException;
import com.workspacepi.apiquoteflow.application.usuarios.exceptions.UsuarioNaoEncontradoException;
import com.workspacepi.apiquoteflow.application.usuarios.exceptions.UsuarioPermissaoNegadaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    // Lida com a UsuarioNaoEncontradoException
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getId_usuario(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Lida com a UsuarioEmailCadastradoException
    @ExceptionHandler(UsuarioEmailCadastradoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioEmailCadastradoException(UsuarioEmailCadastradoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getEmail(),
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Lida com a UsuarioNaoAutenticadoException
    @ExceptionHandler(UsuarioNaoAutenticadoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioNaoAutenticadoException(UsuarioNaoAutenticadoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Lida com a UsuarioPermissaoNegadaException
    @ExceptionHandler(UsuarioPermissaoNegadaException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioPermissaoNegadaException(UsuarioPermissaoNegadaException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getPermissao(),
                HttpStatus.FORBIDDEN.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    //  Lida com o ProdutoNaoEncontradoException
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getId_produto(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProdutoSkuNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleProdutoSkuNaoEncontradoException(ProdutoSkuNaoEncontradoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getSku(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProdutoSkuCadastradoException.class)
    public ResponseEntity<ErrorResponse> handleProdutoSkuCadastradoException(ProdutoSkuCadastradoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getSku(),
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmpresaCNPJNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleEmpresaCNPJNaoEncontradoException(EmpresaCNPJNaoEncontradoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getCnpj(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmpresaIdNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleEmpresaIdNaoEncontradaException(EmpresaIdNaoEncontradaException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getId_empresa(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmpresaCNPJCadastradoException.class)
    public ResponseEntity<ErrorResponse> handleEmpresaCNPJCadastradoException(EmpresaCNPJCadastradoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getCnpj(),
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmpresaEmailCadastradoException.class)
    public ResponseEntity<ErrorResponse> handleEmpresaEmailCadastradoException(EmpresaEmailCadastradoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getEmail(),
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmpresaEmailNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleEmpresaEmailNaoEncontradoException(EmpresaEmailNaoEncontradoException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                e.getEmail(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Método genérico para outras exceções
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("Erro desconhecido", 400);
        LOGGER.warn("[Exception] {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

    // Método genérico para outras exceções
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
