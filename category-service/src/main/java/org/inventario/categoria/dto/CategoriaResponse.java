package org.inventario.categoria.dto;

import lombok.*;
import org.inventario.categoria.entity.Categoria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponse {
    private Long id;
    private String nombre;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nombre = categoria.getNombre();
    }
}