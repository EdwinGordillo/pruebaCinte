package org.inventario.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.inventario.entity.Movimiento;

@ApplicationScoped
public class MovimientoRepository implements PanacheRepository<Movimiento> {
}