package org.inventario.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {

    private String nombre;
    private Integer stock;
    private Integer stockMinimo;
    private Long idCategoria;
}