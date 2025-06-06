package org.inventario.producto.service;

import org.inventario.producto.entity.Producto;

import java.util.List;

public interface ProductoService {

    Producto crear(Producto producto);

    List<Producto> listar();

    Producto obtener(Long id);

    Producto actualizar(Long id, Producto producto);

    boolean eliminar(Long id);
    
}