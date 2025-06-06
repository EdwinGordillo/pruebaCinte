package org.inventario.movimiento.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.inventario.movimiento.entity.Producto;

@ApplicationScoped
public class ProductoRepository implements PanacheRepository<Producto> {
}