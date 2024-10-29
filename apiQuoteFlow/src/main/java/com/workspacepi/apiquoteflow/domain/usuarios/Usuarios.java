package com.workspacepi.apiquoteflow.domain.usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Usuarios implements UserDetails {
    private UUID id_usuario;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private UUID id_empresa;
    private Permissoes permissao;

    // Construtor para inserção no banco de dados
    public Usuarios(String nome, String email, String senha, String telefone, UUID id_empresa) {
        this.id_usuario = UUID.randomUUID(); // Gera um UUID aleatório
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.id_empresa = id_empresa;
        this.permissao = Permissoes.USUARIO;

    }

    //  Métodos do UserDetails

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.permissao == Permissoes.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_EMPRESA"),
                new SimpleGrantedAuthority("ROLE_ASSOCIADO"), new SimpleGrantedAuthority("ROLE_USUARIO"));

        else if(this.permissao == Permissoes.EMPRESA) return List.of(new SimpleGrantedAuthority("ROLE_EMPRESA"),
                new SimpleGrantedAuthority("ROLE_ASSOCIADO"), new SimpleGrantedAuthority("ROLE_USUARIO"));

        else if(this.permissao == Permissoes.ASSOCIADO) return List.of(new SimpleGrantedAuthority("ROLE_ASSOCIADO"),
                new SimpleGrantedAuthority("ROLE_USUARIO"));

        else return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return email;
    }
}
