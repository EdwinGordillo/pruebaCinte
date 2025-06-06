package org.inventario.producto.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.inventario.producto.entity.Categoria;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {
}