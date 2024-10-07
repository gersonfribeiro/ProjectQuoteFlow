package com.workspacepi.apiquoteflow.application.enderecos;

import com.workspacepi.apiquoteflow.application.enderecos.exceptions.EnderecoNaoEncontradoException;
import com.workspacepi.apiquoteflow.domain.enderecos.Endereco;
import com.workspacepi.apiquoteflow.domain.enderecos.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// Nossa classe de serviços dos enderecos, aqui definimos as regras de negócios do nosso projeto no que diz respeito aos enderecos

@Service
public class EnderecosService {

//  Atributo do nosso repository onde temos a nossa interface que
//  declara a abstração que devemos possuir nos métodos crud usando o JDBC
//  assim como o seu construtor

    private final EnderecoRepository enderecoRepository;
    public EnderecosService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

//  Retorno do método findAll

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

//  Retorno do método findById com a possíbilidade de retornar uma exception criada manualmente

    public Endereco findById(UUID id_endereco) throws Exception{
        Endereco endereco = enderecoRepository.findById(id_endereco);

        if(endereco == null)
            throw new EnderecoNaoEncontradoException(id_endereco);

        return endereco;
    }

    public Endereco cadastrarEndereco(EnderecosCreateCommand enderecosCreateCommand) throws Exception {
        Endereco enderecoDomain = enderecosCreateCommand.toEndereco();
        enderecoRepository.cadastrarEndereco(enderecoDomain);

        return findById(enderecoDomain.getId_endereco());
    }

    public Endereco modificarEndereco(EnderecosUpdateCommand enderecosUpdateCommand, UUID id_endereco) throws Exception {
        Endereco enderecoDomain = enderecoRepository.findById(id_endereco);

        if (enderecoDomain == null) {
            throw new EnderecoNaoEncontradoException(id_endereco);
        }

        enderecoRepository.modificarEndereco(enderecosUpdateCommand.toEndereco(id_endereco));
        return findById(id_endereco);
    }

    public void deletarEnderecoById(UUID id_endereco) throws Exception {
        Endereco enderecoDomain = enderecoRepository.findById(id_endereco);

        if (enderecoDomain == null) {
            throw new EnderecoNaoEncontradoException(id_endereco);
        }

        enderecoRepository.deleteEnderecobyId(id_endereco);
    }
}
