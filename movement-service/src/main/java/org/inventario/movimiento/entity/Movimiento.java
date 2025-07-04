package org.inventario.movimiento.entity;

import jakarta.persistence.*;
import lombok.*;
import org.inventario.auth.entity.Usuario;
import org.inventario.producto.entity.Producto;

import java.util.Date;

@Entity
@Table(name = "MOVIMIENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false, length = 10)
    private TipoMovimiento tipo;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA", updatable = false)
    private Date fecha = new Date();

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    public enum TipoMovimiento {
        ENTRADA, SALIDA
    }
}