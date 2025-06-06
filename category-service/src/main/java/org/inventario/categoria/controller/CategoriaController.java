package org.inventario.categoria.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.inventario.dto.CategoriaRequest;
import org.inventario.dto.CategoriaResponse;
import org.inventario.entity.Categoria;
import org.inventario.service.CategoriaService;

import java.util.List;
import java.util.stream.Collectors;

@Path("/categorias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Categorías", description = "CRUD para categorías de productos")
public class CategoriaController {

    @Inject
    CategoriaService categoriaService;

    @POST
    @RolesAllowed("user")
    @Operation(summary = "Crear una nueva categoría")
    public Response crearCategoria(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        Categoria creada = categoriaService.crear(categoria);
        return Response.status(Response.Status.CREATED).entity(new CategoriaResponse(creada)).build();
    }

    @GET
    @RolesAllowed("user")
    @Operation(summary = "Listar todas las categorías")
    public List<CategoriaResponse> listarCategorias() {
        return categoriaService.listar().stream()
                .map(CategoriaResponse::new)
                .collect(Collectors.toList());
    }

    @GET
    @RolesAllowed("user")
    @Path("/{id}")
    @Operation(summary = "Obtener una categoría por ID")
    public Response obtenerCategoria(@PathParam("id") Long id) {
        Categoria categoria = categoriaService.obtener(id);
        return categoria != null
                ? Response.ok(new CategoriaResponse(categoria)).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @RolesAllowed("user")
    @Path("/{id}")
    @Operation(summary = "Actualizar una categoría por ID")
    public Response actualizarCategoria(@PathParam("id") Long id, CategoriaRequest request) {
        Categoria actualizada = new Categoria();
        actualizada.setNombre(request.getNombre());
        Categoria resultado = categoriaService.actualizar(id, actualizada);
        return resultado != null
                ? Response.ok(new CategoriaResponse(resultado)).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @RolesAllowed("user")
    @Path("/{id}")
    @Operation(summary = "Eliminar una categoría por ID")
    public Response eliminarCategoria(@PathParam("id") Long id) {
        boolean eliminado = categoriaService.eliminar(id);
        return eliminado ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}