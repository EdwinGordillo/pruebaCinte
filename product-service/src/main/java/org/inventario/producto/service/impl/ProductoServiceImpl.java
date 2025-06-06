package org.inventario.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.inventario.entity.Categoria;
import org.inventario.entity.Producto;
import org.inventario.repository.CategoriaRepository;
import org.inventario.repository.ProductoRepository;
import org.inventario.service.ProductoService;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ProductoServiceImpl implements ProductoService {

    @Inject
    ProductoRepository productoRepository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    public Producto crear(Producto producto) {
        producto.setCreatedAt(new Date());
        productoRepository.persist(producto);
        return producto;
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.listAll();
    }

    @Override
    public Producto obtener(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto actualizar(Long id, Producto nuevo) {
        Producto existente = productoRepository.findById(id);
        if (existente == null) return null;
        existente.setNombre(nuevo.getNombre());
        existente.setStock(nuevo.getStock());
        existente.setStockMinimo(nuevo.getStockMinimo());
        existente.setCategoria(nuevo.getCategoria());
        existente.setUpdatedAt(new Date());
        return existente;
    }

    @Override
    public boolean eliminar(Long id) {
        return productoRepository.deleteById(id);
    }
}