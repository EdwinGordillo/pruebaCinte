package org.inventario.categoria.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.inventario.categoria.entity.Categoria;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {
}