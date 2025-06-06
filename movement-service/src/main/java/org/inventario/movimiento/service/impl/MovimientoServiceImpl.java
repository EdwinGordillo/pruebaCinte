package org.inventario.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.inventario.entity.Movimiento;
import org.inventario.entity.Producto;
import org.inventario.entity.Usuario;
import org.inventario.repository.MovimientoRepository;
import org.inventario.repository.ProductoRepository;
import org.inventario.repository.UsuarioRepository;
import org.inventario.service.MovimientoService;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class MovimientoServiceImpl implements MovimientoService {

    @Inject
    MovimientoRepository movimientoRepository;

    @Inject
    ProductoRepository productoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public Movimiento registrar(Movimiento movimiento) {
        Producto producto = productoRepository.findById(movimiento.getProducto().getId());
        if (producto == null) throw new IllegalArgumentException("Producto no encontrado");

        // Actualizar stock según tipo de movimiento
        if (movimiento.getTipo() == Movimiento.TipoMovimiento.ENTRADA) {
            producto.setStock(producto.getStock() + movimiento.getCantidad());
        } else if (movimiento.getTipo() == Movimiento.TipoMovimiento.SALIDA) {
            int nuevoStock = producto.getStock() - movimiento.getCantidad();
            if (nuevoStock < 0) throw new IllegalArgumentException("Stock insuficiente");
            producto.setStock(nuevoStock);
        }

        productoRepository.persist(producto);
        movimiento.setProducto(producto);
        movimiento.setFecha(new Date());

        if (movimiento.getUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(movimiento.getUsuario().getId());
            movimiento.setUsuario(usuario);
        }

        movimientoRepository.persist(movimiento);
        return movimiento;
    }

    @Override
    public List<Movimiento> listar() {
        return movimientoRepository.listAll();
    }

    @Override
    public Movimiento obtener(Long id) {
        return movimientoRepository.findById(id);
    }

    @Override
    public boolean eliminar(Long id) {
        return movimientoRepository.deleteById(id);
    }
}