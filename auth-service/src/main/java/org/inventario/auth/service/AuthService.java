package org.inventario.auth.service;

public interface AuthService {
    boolean validarUsuario(String username, String passwordHash);
}
