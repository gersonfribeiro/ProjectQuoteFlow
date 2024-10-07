package com.workspacepi.apiquoteflow.adapters.http.produtos;

import com.workspacepi.apiquoteflow.application.produtos.ProdutosCreateCommand;
import com.workspacepi.apiquoteflow.application.produtos.ProdutosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//  Esse é o nosso controller, ele define os casos de uso para uma rota de cotacoes usando o método get

@RestController
public class ProdutosController {

//  Definição do nosso atributo Handles assim como o seu construtor

    private final ProdutosHandler produtosHandler;
    public ProdutosController(ProdutosHandler produtosHandler) {
        this.produtosHandler = produtosHandler;
    }

//  Método get para a rota de cotações que devolve todas as cotações
//  (afim de testes, isso não pode ser implementado na aplicação com as cotações).

    @GetMapping("/produtos")
    public ResponseEntity<List<Produtos>> findAll() {
        return produtosHandler.findAll();
    }

//  Método get para a rota de uma cotação especifica.

    @GetMapping("/produtos/{id_produto}")
    public ResponseEntity<Produtos> findById(@PathVariable String id_produto) throws Exception {
        return produtosHandler.findById(id_produto);
    }

//  Método post para solicitar uma nova cotação (necessita de modificações
    @PostMapping("/produtos")
    public ResponseEntity<Produtos> create(@RequestBody ProdutosCreateCommand produtosCreateCommand) throws Exception {

        return produtosHandler.cadastrarProduto(produtosCreateCommand);
    }

//  Método put para solicitar uma nova cotação (necessita de modificações
    @PutMapping("/produtos/{id_produto}")
    public ResponseEntity<Produtos> create(@RequestBody ProdutosUpdateCommand produtosUpdateCommandCommand, @PathVariable String id_produto) throws Exception {

        return produtosHandler.modificarProduto(produtosUpdateCommandCommand, id_produto);
    }

    @DeleteMapping("produtos/{id_produto}")
    public ResponseEntity<String> deleteProdutoById(@PathVariable String id_produto) throws Exception {
        return produtosHandler.deleteProdutoById(id_produto);
    }
}
