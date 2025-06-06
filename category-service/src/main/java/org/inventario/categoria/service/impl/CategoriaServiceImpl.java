package org.inventario.categoria.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.inventario.entity.Categoria;
import org.inventario.repository.CategoriaRepository;
import org.inventario.service.CategoriaService;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    public Categoria crear(Categoria categoria) {
        categoria.setCreatedAt(new Date());
        categoriaRepository.persist(categoria);
        return categoria;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.listAll();
    }

    @Override
    public Categoria obtener(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria actualizar(Long id, Categoria nuevaData) {
        Categoria existente = categoriaRepository.findById(id);
        if (existente == null) return null;
        existente.setNombre(nuevaData.getNombre());
        existente.setUpdatedAt(new Date());
        return existente;
    }

    @Override
    public boolean eliminar(Long id) {
        return categoriaRepository.deleteById(id);
    }
}