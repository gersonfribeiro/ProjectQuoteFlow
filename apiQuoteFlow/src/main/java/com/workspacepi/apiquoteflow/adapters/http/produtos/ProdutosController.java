package com.workspacepi.apiquoteflow.adapters.http.produtos;

import com.workspacepi.apiquoteflow.application.produtos.ProdutosCreateCommand;
import com.workspacepi.apiquoteflow.application.produtos.ProdutosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


//  Esse é o nosso controller, ele define os casos de uso para uma rota de cotacoes usando o método get

@RestController
@RequestMapping("/empresas/{id_empresa}/produtos")
public class ProdutosController {

    private final ProdutosHandler produtosHandler;

    public ProdutosController(ProdutosHandler produtosHandler) {
        this.produtosHandler = produtosHandler;
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> findAllByEmpresa(@PathVariable String id_empresa) {
        try {
            return produtosHandler.findAllByEmpresa(id_empresa);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();  // Retorna erro 400 para UUID inválido
        }
    }

    @GetMapping("/{id_produto}")
    public ResponseEntity<Produtos> findByIdAndEmpresa(@PathVariable String id_empresa, @PathVariable String id_produto) {
        try {
            return produtosHandler.findByIdAndEmpresa(id_produto, id_empresa);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();  // Retorna erro 400 para UUID inválido

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Produtos> cadastrarProdutoInEmpresa(@PathVariable String id_empresa, @RequestBody @Valid ProdutosCreateCommand produtosCreateCommand) {
        try {
            return produtosHandler.cadastrarProdutoInEmpresa(produtosCreateCommand, id_empresa);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();  // Retorna erro 400 para UUID inválido

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id_produto}")
    public ResponseEntity<Produtos> modificarProdutoInEmpresa(@PathVariable String id_empresa, @PathVariable String id_produto, @RequestBody @Valid ProdutosUpdateCommand produtosUpdateCommand) {
        try {
            return produtosHandler.modificarProdutoInEmpresa(produtosUpdateCommand, id_produto, id_empresa);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();  // Retorna erro 400 para UUID inválido

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id_produto}")
    public ResponseEntity<Void> deleteProdutoByIdAndEmpresa(@PathVariable String id_empresa, @PathVariable String id_produto) {
        try {
            return produtosHandler.deleteProdutoByIdAndEmpresa(id_empresa, id_produto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();  // Retorna erro 400 para UUID inválido

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
