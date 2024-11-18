package com.workspacepi.apiquoteflow.application.produtos;

import com.workspacepi.apiquoteflow.application.empresas.exceptions.EmpresaIdNaoEncontradaException;
import com.workspacepi.apiquoteflow.application.produtos.exceptions.ProdutoNaoEncontradoException;
import com.workspacepi.apiquoteflow.application.produtos.exceptions.ProdutoSkuCadastradoException;
import com.workspacepi.apiquoteflow.application.produtos.exceptions.ProdutoSkuNaoEncontradoException;
import com.workspacepi.apiquoteflow.domain.empresas.EmpresasRepository;
import com.workspacepi.apiquoteflow.domain.produtos.Produtos;
import com.workspacepi.apiquoteflow.domain.produtos.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutosService {
    private final  ProdutosRepository produtosRepository;
    private final EmpresasRepository empresasRepository;

    public ProdutosService(ProdutosRepository produtosRepository, EmpresasRepository empresasRepository) {
        this.produtosRepository = produtosRepository;
        this.empresasRepository = empresasRepository;
    }

    public List<Produtos> findAllByEmpresa(UUID id_empresa) {
        if(empresasRepository.findById(id_empresa) == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa);

        return produtosRepository.findAllByEmpresa(id_empresa);
    }

    public Produtos findByIdAndEmpresa(UUID id_produto, UUID id_empresa) {
        Produtos produto = produtosRepository.findByIdAndEmpresa(id_produto, id_empresa);

        if (produto == null) {
            throw new ProdutoNaoEncontradoException(id_produto);
        }

        return produto;
    }

    public Produtos findBySKUAndEmpresa(String sku, UUID id_empresa) {
        if(empresasRepository.findById(id_empresa) == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa);

        Produtos produto = produtosRepository.findBySKUAndEmpresa(sku, id_empresa);

        if (produto == null) {
            throw new ProdutoSkuNaoEncontradoException(sku);
        }

        return produto;
    }

    public Produtos cadastrarProdutoInEmpresa(ProdutosCreateCommand produtosCreateCommand, UUID id_empresa) {
        if(empresasRepository.findById(id_empresa) == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa);

        // Verificar se o SKU já existe na mesma empresa
        if (produtosRepository.findBySKUAndEmpresa(produtosCreateCommand.getSku(), id_empresa) != null)
            throw new ProdutoSkuCadastradoException(produtosCreateCommand.getSku());

        Produtos produtoDomain = produtosCreateCommand.toProduto();
        produtoDomain.setId_empresa(id_empresa);
        produtosRepository.cadastrarProdutoInEmpresa(produtoDomain, id_empresa);

        return findByIdAndEmpresa(produtoDomain.getId_produto(), id_empresa);
    }

    public Produtos modificarProdutoInEmpresa(ProdutosUpdateCommand produtosUpdateCommand, UUID id_empresa, UUID id_produto) {
        if(empresasRepository.findById(id_empresa) == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa);

        Produtos produtoDomain = produtosRepository.findByIdAndEmpresa(id_produto, id_empresa);

        if (produtoDomain == null)
            throw new ProdutoNaoEncontradoException(id_produto);

        // Verificar se o SKU já existe em outro produto da mesma empresa
        Produtos produtoValidado = produtosRepository.findBySKUAndEmpresa(produtosUpdateCommand.getSku(), id_empresa);
        if (produtoValidado != null)
            throw new ProdutoSkuCadastradoException(produtosUpdateCommand.getSku());

        // Atualizar o produto com os novos valores
        produtoDomain = produtosUpdateCommand.toProduto(id_produto);
        produtoDomain.setId_empresa(id_empresa);

        produtosRepository.modificarProdutoInEmpresa(produtoDomain, id_empresa);

        return produtoDomain;
    }

    public void deleteProdutoByIdAndEmpresa(UUID id_empresa, UUID id_produto) {
        if(empresasRepository.findById(id_empresa) == null)
            throw new EmpresaIdNaoEncontradaException(id_empresa);

        Produtos produtoDomain = produtosRepository.findByIdAndEmpresa(id_produto, id_empresa);

        if (produtoDomain == null)
            throw new ProdutoNaoEncontradoException(id_produto);

        produtosRepository.deleteProdutoByIdAndEmpresa(id_produto, id_empresa);
    }
}
