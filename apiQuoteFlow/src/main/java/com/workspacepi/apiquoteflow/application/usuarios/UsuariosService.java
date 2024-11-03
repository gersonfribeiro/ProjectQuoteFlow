package com.workspacepi.apiquoteflow.application.usuarios;

import com.workspacepi.apiquoteflow.application.usuarios.exceptions.UsuarioEmailCadastradoException;
import com.workspacepi.apiquoteflow.application.usuarios.exceptions.UsuarioNaoEncontradoException;
import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import com.workspacepi.apiquoteflow.domain.usuarios.UsuariosRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuariosService(UsuariosRepository usuariosRepository, PasswordEncoder passwordEncoder) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuarios> findAll() {
        return usuariosRepository.findAll();
    }

    public Usuarios findById(UUID id_usuario) throws Exception {
        Usuarios usuario = usuariosRepository.findById(id_usuario);

        if (usuario == null)
            throw new UsuarioNaoEncontradoException(id_usuario);

        return usuario;
    }

    public Usuarios cadastrarUsuario(UsuariosCreateCommand usuarioCreateCommand) throws Exception {
        if(usuariosRepository.findByEmail(usuarioCreateCommand.getEmail_usuario()).isPresent()) {
            throw new UsuarioEmailCadastradoException(usuarioCreateCommand.getEmail_usuario());
        }

        String encryptedPassword = passwordEncoder.encode(usuarioCreateCommand.getSenha_usuario());
        usuarioCreateCommand.setSenha_usuario(encryptedPassword);

        Usuarios usuarioDomain = usuarioCreateCommand.toUsuario();
        usuariosRepository.cadastrarUsuario(usuarioDomain);

        return findById(usuarioDomain.getId_usuario());
    }

    public Usuarios modificarUsuario(UsuariosUpdateCommand usuarioUpdateCommand, UUID id_usuario) throws Exception {
        // Busca o usuário pelo ID
        Usuarios usuarioDomain = usuariosRepository.findById(id_usuario);

        // Verifica se o usuário com o ID especificado existe
        if (usuarioDomain == null) {
            throw new UsuarioNaoEncontradoException(id_usuario);
        }

        // Verifica se o novo e-mail já está cadastrado
        Optional<UserDetails> usuarioExistente = usuariosRepository.findByEmail(usuarioUpdateCommand.getEmail_usuario());

        if (usuarioExistente.isPresent()) {
            // Casting para acessar o `id_usuario`, assumindo que `UserDetails` é implementado por `Usuarios`
            Usuarios usuario = (Usuarios) usuarioExistente.get();

            // Verifica se o id encontrado é diferente do id do usuário que está sendo atualizado
            if (!usuario.getId_usuario().equals(id_usuario)) {
                throw new UsuarioEmailCadastradoException(usuarioUpdateCommand.getEmail_usuario());
            }
        }

        // Atualiza o usuário no repositório
        usuariosRepository.modificarUsuario(usuarioUpdateCommand.toUsuario(id_usuario));
        return findById(id_usuario);
    }


    // Método de exclusão de usuário
    public void deleteUsuarioById(UUID id_usuario) throws Exception {
        Usuarios usuarioDomain = usuariosRepository.findById(id_usuario); // Busca o usuário pelo ID

        if (usuarioDomain != null) { // Verifica se o usuário existe
            usuariosRepository.deleteUsuarioById(id_usuario); // Chama o repositório para deletar
        } else {
            throw new UsuarioNaoEncontradoException(id_usuario);
        }
    }
}
