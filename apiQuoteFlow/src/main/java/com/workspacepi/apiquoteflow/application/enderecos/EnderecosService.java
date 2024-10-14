package com.workspacepi.apiquoteflow.application.enderecos;

import com.workspacepi.apiquoteflow.application.enderecos.exceptions.EnderecoNaoEncontradoException;
import com.workspacepi.apiquoteflow.domain.enderecos.Enderecos;
import com.workspacepi.apiquoteflow.domain.enderecos.EnderecosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// Nossa classe de serviços dos enderecos, aqui definimos as regras de negócios do nosso projeto no que diz respeito aos enderecos

@Service
public class EnderecosService {

//  Atributo do nosso repository onde temos a nossa interface que
//  declara a abstração que devemos possuir nos métodos crud usando o JDBC
//  assim como o seu construtor

    private final EnderecosRepository enderecosRepository;
    public EnderecosService(EnderecosRepository enderecosRepository) {
        this.enderecosRepository = enderecosRepository;
    }

//  Retorno do método findAll

    public List<Enderecos> findAll() {
        return enderecosRepository.findAll();
    }

//  Retorno do método findById com a possíbilidade de retornar uma exception criada manualmente

    public Enderecos findById(UUID id_endereco) throws Exception{
        Enderecos enderecos = enderecosRepository.findById(id_endereco);

        if(enderecos == null)
            throw new EnderecoNaoEncontradoException(id_endereco);

        return enderecos;
    }

    public Enderecos cadastrarEndereco(EnderecosCreateCommand enderecosCreateCommand) throws Exception {
        Enderecos enderecosDomain = enderecosCreateCommand.toEndereco();
        enderecosRepository.cadastrarEndereco(enderecosDomain);

        return findById(enderecosDomain.getId_endereco());
    }

    public Enderecos modificarEndereco(EnderecosUpdateCommand enderecosUpdateCommand, UUID id_endereco) throws Exception {
        Enderecos enderecosDomain = enderecosRepository.findById(id_endereco);

        if (enderecosDomain == null) {
            throw new EnderecoNaoEncontradoException(id_endereco);
        }

        enderecosRepository.modificarEndereco(enderecosUpdateCommand.toEndereco(id_endereco));
        return findById(id_endereco);
    }

    public void deletarEnderecoById(UUID id_endereco) throws Exception {
        Enderecos enderecosDomain = enderecosRepository.findById(id_endereco);

        if (enderecosDomain == null) {
            throw new EnderecoNaoEncontradoException(id_endereco);
        }

        enderecosRepository.deleteEnderecobyId(id_endereco);
    }
}
