package com.workspacepi.apiquoteflow.application.usuarios;

import com.workspacepi.apiquoteflow.domain.usuarios.Permissoes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class SecurityUtils {

    public static boolean usuarioTemRoleAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

    public static Permissoes getPermissaoDoUsuarioAtual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica se o usuário está autenticado
        if (authentication == null || !authentication.isAuthenticated()) {
            return Permissoes.ANONYMOUS; // Retorno caso não esteja autenticado
        }

        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(role -> role.startsWith("ROLE_"))
                .map(role -> Permissoes.valueOf(role.substring(5))) // Remove o prefixo "ROLE_" e converte para enum
                .findFirst()
                .orElse(Permissoes.ANONYMOUS); // Retorno caso não encontre um valor válido
    }

}
