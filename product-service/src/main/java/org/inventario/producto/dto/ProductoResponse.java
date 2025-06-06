package org.inventario.producto.dto;

import lombok.*;
import org.inventario.producto.entity.Producto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {

    private Long id;
    private String nombre;
    private Integer stock;
    private Integer stockMinimo;
    private Long idCategoria;

    public ProductoResponse(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.stock = producto.getStock();
        this.stockMinimo = producto.getStockMinimo();
        this.idCategoria = producto.getCategoria().getId();
    }
}