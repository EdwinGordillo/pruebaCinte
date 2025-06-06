package org.inventario.auth.service.impl;

import org.inventario.auth.repository.UsuarioRepository;
import org.inventario.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public boolean validarUsuario(String username, String password) {
        return usuarioRepository.find("username", username)
            .firstResultOptional()
            .map(user -> BCrypt.checkpw(password, user.getPasswordHash()))
            .orElse(false);
    }
}