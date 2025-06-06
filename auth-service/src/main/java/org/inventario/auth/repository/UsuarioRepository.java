package org.inventario.auth.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.inventario.auth.entity.Usuario;

import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Optional<Usuario> findByUsernameAndPasswordHash(String username, String passwordHash) {
        return find("username = ?1 and passwordHash = ?2", username, passwordHash).firstResultOptional();
    }
}