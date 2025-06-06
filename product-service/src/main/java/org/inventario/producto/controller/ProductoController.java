package org.inventario.producto.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.inventario.producto.dto.ProductoRequest;
import org.inventario.producto.dto.ProductoResponse;
import org.inventario.categoria.entity.Categoria;
import org.inventario.producto.entity.Producto;
import org.inventario.producto.service.ProductoService;

import java.util.List;
import java.util.stream.Collectors;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Productos", description = "CRUD para productos de inventario")
public class ProductoController {

    @Inject
    ProductoService productoService;

    @POST
    @RolesAllowed("user")
    @Operation(summary = "Crear un nuevo producto")
    public Response crearProducto(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setStock(request.getStock());
        producto.setStockMinimo(request.getStockMinimo());
        producto.setCategoria(new Categoria());
        producto.getCategoria().setId(request.getIdCategoria());

        Producto creado = productoService.crear(producto);
        return Response.status(Response.Status.CREATED).entity(new ProductoResponse(creado)).build();
    }

    @GET
    @RolesAllowed("user")
    @Operation(summary = "Listar todos los productos")
    public List<ProductoResponse> listarProductos() {
        return productoService.listar().stream()
                .map(ProductoResponse::new)
                .collect(Collectors.toList());
    }

    @GET
    @RolesAllowed("user")
    @Path("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public Response obtenerProducto(@PathParam("id") Long id) {
        Producto producto = productoService.obtener(id);
        return producto != null
                ? Response.ok(new ProductoResponse(producto)).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @RolesAllowed("user")
    @Path("/{id}")
    @Operation(summary = "Actualizar un producto")
    public Response actualizarProducto(@PathParam("id") Long id, ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setStock(request.getStock());
        producto.setStockMinimo(request.getStockMinimo());
        producto.setCategoria(new Categoria());
        producto.getCategoria().setId(request.getIdCategoria());

        Producto actualizado = productoService.actualizar(id, producto);
        return actualizado != null
                ? Response.ok(new ProductoResponse(actualizado)).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @RolesAllowed("user")
    @Path("/{id}")
    @Operation(summary = "Eliminar un producto por ID")
    public Response eliminarProducto(@PathParam("id") Long id) {
        boolean eliminado = productoService.eliminar(id);
        return eliminado ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}