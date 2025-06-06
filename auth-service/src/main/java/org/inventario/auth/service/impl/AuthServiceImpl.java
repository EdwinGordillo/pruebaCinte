package org.inventario.auth.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.inventario.auth.entity.Usuario;
import org.inventario.auth.repository.UsuarioRepository;
import org.inventario.auth.service.AuthService;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public boolean validarUsuario(String username, String password) {
        return usuarioRepository.findByUsername(username)
                .map(user -> BCrypt.checkpw(password, user.getPasswordHash()))
                .orElse(false);
    }
}   