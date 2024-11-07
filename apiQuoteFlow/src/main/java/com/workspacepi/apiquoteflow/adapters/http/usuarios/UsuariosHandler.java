package com.workspacepi.apiquoteflow.adapters.http.usuarios;

import com.workspacepi.apiquoteflow.application.usuarios.UsuarioResponseDTO;
import com.workspacepi.apiquoteflow.application.usuarios.UsuariosCreateCommand;
import com.workspacepi.apiquoteflow.application.usuarios.UsuariosService;
import com.workspacepi.apiquoteflow.application.usuarios.UsuariosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UsuariosHandler {
    private final UsuariosService usuariosService;

    public UsuariosHandler(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    public ResponseEntity<List<Usuarios>> findAll() {
        List<Usuarios> usuario = usuariosService.findAll();
        return ResponseEntity.ok(usuario);
    }

    public ResponseEntity<UsuarioResponseDTO> findById(String id_usuario) throws Exception {
        Optional<Usuarios> usuario = Optional.ofNullable(usuariosService.findById(UUID.fromString(id_usuario)));

        if (usuario.isPresent()) {
            UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                    usuario.get().getNome(),
                    usuario.get().getEmail(),
                    usuario.get().getSenha(),
                    usuario.get().getPermissao(),
                    usuario.get().getTelefone(),
                    usuario.get().getId_empresa()
            );
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<Optional<Usuarios>> cadastrarUsuario(UsuariosCreateCommand usuarioCreateCommand) throws Exception {
        Optional<Usuarios> usuario = Optional.ofNullable(usuariosService.cadastrarUsuario(usuarioCreateCommand));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    public ResponseEntity<Optional<Usuarios>> modificarUsuario(UsuariosUpdateCommand usuarioUpdateCommand, String id_usuario) throws Exception {
        Optional<Usuarios> usuario = Optional.ofNullable(usuariosService.modificarUsuario(usuarioUpdateCommand, UUID.fromString(id_usuario)));
        return ResponseEntity.ok(usuario);
    }
    
    public ResponseEntity<String> deleteUsuarioById(String id_usuario) throws Exception {
        usuariosService.deleteUsuarioById(UUID.fromString(id_usuario));
        return ResponseEntity.noContent().build();
    }
}
