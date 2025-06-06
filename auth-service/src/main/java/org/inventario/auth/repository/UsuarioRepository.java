package org.inventario.auth.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.inventario.auth.entity.Usuario;

import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public Optional<Usuario> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
}