package org.inventario.movimiento.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.inventario.dto.MovimientoRequest;
import org.inventario.dto.MovimientoResponse;
import org.inventario.entity.Movimiento;
import org.inventario.entity.Producto;
import org.inventario.entity.Usuario;
import org.inventario.service.MovimientoService;

import java.util.List;
import java.util.stream.Collectors;

@Path("/movimientos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Movimientos", description = "Entradas y salidas de inventario")
public class MovimientoController {

    @Inject
    MovimientoService movimientoService;

    @POST
    @RolesAllowed("user")
    @Operation(summary = "Registrar un movimiento de entrada o salida")
    public Response registrarMovimiento(MovimientoRequest request) {
        Movimiento movimiento = new Movimiento();
        movimiento.setTipo(Movimiento.TipoMovimiento.valueOf(request.getTipo().toUpperCase()));
        movimiento.setCantidad(request.getCantidad());
        movimiento.setProducto(new Producto());
        movimiento.getProducto().setId(request.getIdProducto());

        if (request.getIdUsuario() != null) {
            movimiento.setUsuario(new Usuario());
            movimiento.getUsuario().setId(request.getIdUsuario());
        }

        try {
            Movimiento creado = movimientoService.registrar(movimiento);
            return Response.status(Response.Status.CREATED).entity(new MovimientoResponse(creado)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @RolesAllowed("user")
    @Operation(summary = "Listar todos los movimientos")
    public List<MovimientoResponse> listar() {
        return movimientoService.listar().stream()
                .map(MovimientoResponse::new)
                .collect(Collectors.toList());
    }

    @GET
    @RolesAllowed("user")
    @Path("/{id}")
    @Operation(summary = "Obtener un movimiento por ID")
    public Response obtener(@PathParam("id") Long id) {
        Movimiento mov = movimientoService.obtener(id);
        return mov != null
                ? Response.ok(new MovimientoResponse(mov)).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @RolesAllowed("user")
    @Path("/{id}")
    @Operation(summary = "Eliminar un movimiento por ID")
    public Response eliminar(@PathParam("id") Long id) {
        boolean eliminado = movimientoService.eliminar(id);
        return eliminado ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}