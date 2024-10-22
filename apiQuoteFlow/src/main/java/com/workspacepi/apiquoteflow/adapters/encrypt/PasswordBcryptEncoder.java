package com.workspacepi.apiquoteflow.adapters.encrypt;

import com.workspacepi.apiquoteflow.application.usuarios.EncoderPassword;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;


@Component
public class PasswordBcryptEncoder implements EncoderPassword {
    private final PasswordEncoder passwordEncoder;

    public PasswordBcryptEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String senha) {
        return passwordEncoder.encode(senha);
    }
}
