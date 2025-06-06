package org.inventario.movimiento.dto;

import lombok.*;
import org.inventario.movimiento.entity.Movimiento;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoResponse {

    private Long id;
    private String tipo;
    private Integer cantidad;
    private Date fecha;
    private Long idProducto;
    private Long idUsuario;

    public MovimientoResponse(Movimiento movimiento) {
        this.id = movimiento.getId();
        this.tipo = movimiento.getTipo().name();
        this.cantidad = movimiento.getCantidad();
        this.fecha = movimiento.getFecha();
        this.idProducto = movimiento.getProducto().getId();
        this.idUsuario = movimiento.getUsuario() != null ? movimiento.getUsuario().getId() : null;
    }
}