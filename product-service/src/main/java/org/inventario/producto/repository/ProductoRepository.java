package org.inventario.producto.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.inventario.entity.Producto;

@ApplicationScoped
public class ProductoRepository implements PanacheRepository<Producto> {
}