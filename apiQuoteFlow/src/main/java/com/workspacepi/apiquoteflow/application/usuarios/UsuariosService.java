package com.workspacepi.apiquoteflow.application.usuarios;

import com.workspacepi.apiquoteflow.application.usuarios.exceptions.UsuarioNaoEncontradoException;
import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import com.workspacepi.apiquoteflow.domain.usuarios.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
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
        Usuarios usuarioDomain = usuarioCreateCommand.toUsuario();
        usuariosRepository.cadastrarUsuario(usuarioDomain);

        return findById(usuarioDomain.getId_usuario());
    }

    public Usuarios modificarUsuario(UsuariosUpdateCommand usuarioUpdateCommand, UUID id_usuario) throws Exception {
        Usuarios usuarioDomain = usuariosRepository.findById(id_usuario);

        if (usuarioDomain == null)
            throw new UsuarioNaoEncontradoException(id_usuario);

        usuariosRepository.modificarUsuario(usuarioUpdateCommand.toUsuario(id_usuario));
        return findById(id_usuario);
    }

    // Método de exclusão de usuário
    public void deleteUsuarioById(UUID id_usuario) throws Exception {
        Usuarios usuarioDomain = usuariosRepository.findById(id_usuario); // Busca o usuário pelo ID

        if (usuarioDomain != null) { // Verifica se o usuário existe
            usuariosRepository.deleteUsuarioById(id_usuario); // Chama o repositório para deletar
        } else {
            throw new Exception("Usuário não encontrado."); // Lança exceção se o usuário não for encontrado
        }
    }
}
