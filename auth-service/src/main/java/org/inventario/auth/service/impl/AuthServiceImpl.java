package org.inventario.auth.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.inventario.auth.dto.UsuarioResponse;
import org.inventario.auth.entity.Usuario;
import org.inventario.auth.repository.UsuarioRepository;
import org.inventario.auth.service.AuthService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Usuario registrar(Usuario usuario) {
        String hash = BCrypt.hashpw(usuario.getPasswordHash(), BCrypt.gensalt());
        usuario.setPasswordHash(hash);
        usuario.setCreatedAt(new Date());
        return usuarioRepository.persistAndFlush(usuario);
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.listAll();
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id);
        if (existente != null) {
            existente.setUsername(usuario.getUsername());

            // Si se envía un nuevo password, lo actualiza
            if (usuario.getPasswordHash() != null && !usuario.getPasswordHash().isEmpty()) {
                String hash = BCrypt.hashpw(usuario.getPasswordHash(), BCrypt.gensalt());
                existente.setPasswordHash(hash);
            }

            existente.setUpdatedAt(new Date());
            return usuarioRepository.getEntityManager().merge(existente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Long id) {
        Usuario existente = usuarioRepository.findById(id);
        if (existente != null) {
            usuarioRepository.delete(existente);
            return true;
        }
        return false;
    }
}