package com.workspacepi.apiquoteflow.application.produtos;

import com.workspacepi.apiquoteflow.application.produtos.exceptions.ProdutoNaoEncontradoException;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import com.workspacepi.apiquoteflow.domain.produtos.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutosService {
    private final  ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public List<Produtos> findAll() { return produtosRepository.findAll(); }

    public Produtos findById(UUID id_produto) throws Exception {
        Produtos produto = produtosRepository.findById(id_produto);

        if (produto == null)
            throw new ProdutoNaoEncontradoException(id_produto);

        return produto;
    }

    public Produtos cadastrarProduto(ProdutosCreateCommand produtosCreateCommand) throws Exception {
        Produtos produtoDomain = produtosCreateCommand.toProduto();
        produtosRepository.cadastrarProduto(produtoDomain);

        return findById(produtoDomain.getId_produto());
    }

    public Produtos modificarProduto(ProdutosUpdateCommand produtosUpdateCommand, UUID id_produto) throws Exception {
        Produtos produtoDomain = produtosRepository.findById(id_produto);

        if (produtoDomain == null)
            throw new ProdutoNaoEncontradoException(id_produto);

        produtosRepository.modificarProduto(produtosUpdateCommand.toProduto(id_produto));
        return findById(id_produto);
    }

    public void deleteProdutoById(UUID id_produto) throws Exception {
        Produtos produtoDomain = produtosRepository.findById(id_produto);

        if (produtoDomain == null)
            throw new ProdutoNaoEncontradoException(id_produto);

        produtosRepository.deleteProdutoById(id_produto);
    }
}
