package com.workspacepi.apiquoteflow.adapters.http.usuarios;

import com.workspacepi.apiquoteflow.application.usuarios.UsuariosCreateCommand;
import com.workspacepi.apiquoteflow.application.usuarios.UsuariosUpdateCommand;
import com.workspacepi.apiquoteflow.domain.usuarios.Usuarios;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuariosController {
    private final UsuariosHandler usuariosHandler;

    public UsuariosController(UsuariosHandler usuariosHandler) {
        this.usuariosHandler = usuariosHandler;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuarios>> findAll() {
        return usuariosHandler.findAll();
    }

    @GetMapping("/usuarios/{id_usuario}")
    public ResponseEntity<Usuarios> findById(@PathVariable String id_usuario) throws Exception {
        return usuariosHandler.findById(id_usuario);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuarios> cadastrarUsuario(@RequestBody UsuariosCreateCommand usuarioCreateCommand) throws Exception {
        return usuariosHandler.cadastrarUsuario(usuarioCreateCommand);
    }

    @PutMapping("/usuarios/{id_usuario}")
    public ResponseEntity<Usuarios> modificarUsuario(@RequestBody UsuariosUpdateCommand usurioUpdateCommand, @PathVariable String id_usuario) throws Exception {
        return usuariosHandler.modificarUsuario(usurioUpdateCommand, id_usuario);
    }

    @DeleteMapping("/usuarios/{id_usuario}")
    public ResponseEntity<String> deleteUsuarioById(@PathVariable String id_usuario) throws Exception {
        return usuariosHandler.deleteUsuarioById(id_usuario);
    }
}
