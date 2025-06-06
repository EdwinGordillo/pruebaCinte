package org.inventario.movimiento.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoRequest {

    private String tipo; // "ENTRADA" o "SALIDA"
    private Integer cantidad;
    private Long idProducto;
    private Long idUsuario;
}