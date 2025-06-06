package org.inventario.categoria.service;

import org.inventario.entity.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria crear(Categoria categoria);

    List<Categoria> listar();

    Categoria obtener(Long id);

    Categoria actualizar(Long id, Categoria categoria);

    boolean eliminar(Long id);
    
}