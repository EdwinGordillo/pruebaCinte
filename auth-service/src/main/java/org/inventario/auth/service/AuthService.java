package org.inventario.auth.service;

import org.inventario.auth.entity.Usuario;

import java.util.List;

public interface AuthService {
    boolean validarUsuario(String username, String password);

    Usuario registrar(Usuario usuario);

    List<Usuario> obtenerTodos();

    Usuario obtenerPorId(Long id);

    Usuario actualizar(Long id, Usuario usuario);

    boolean eliminar(Long id);
}