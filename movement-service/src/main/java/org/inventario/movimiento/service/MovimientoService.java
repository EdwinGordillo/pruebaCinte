package org.inventario.movimiento.service;

import org.inventario.movimiento.entity.Movimiento;

import java.util.List;

public interface MovimientoService {

    Movimiento registrar(Movimiento movimiento);

    List<Movimiento> listar();

    Movimiento obtener(Long id);

    boolean eliminar(Long id);
    
}
