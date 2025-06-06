package org.inventario.auth.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.inventario.auth.dto.LoginRequest;
import org.inventario.auth.dto.LoginResponse;
import org.inventario.auth.dto.UsuarioResponse;
import org.inventario.auth.entity.Usuario;
import org.inventario.auth.service.AuthService;
import org.inventario.auth.util.JwtUtil;

import java.util.List;
import java.util.stream.Collectors;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Autenticación", description = "Operaciones de login y gestión de usuarios")
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    @Operation(summary = "Iniciar sesión", description = "Devuelve un token JWT si las credenciales son válidas")
    public Response login(LoginRequest request) {
        if (authService.validarUsuario(request.getUsername(), request.getPassword())) {
            String token = JwtUtil.generarToken(request.getUsername());
            return Response.ok(new LoginResponse(true, "Autenticación exitosa", token)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new LoginResponse(false, "Credenciales inválidas", null)).build();
        }
    }

    @POST
    @Path("/register")
    @Operation(summary = "Registrar nuevo usuario", description = "Crea un nuevo usuario con contraseña encriptada")
    public Response registrarUsuario(Usuario usuario) {
        Usuario creado = authService.registrar(usuario);
        return Response.status(Response.Status.CREATED).entity(new UsuarioResponse(creado)).build();
    }

    @GET
    @RolesAllowed("user")
    @Path("/usuarios")
    @Operation(summary = "Obtener todos los usuarios", description = "Requiere autenticación JWT")
    public Response listarUsuarios() {
        List<Usuario> usuarios = authService.obtenerTodos();
        List<UsuarioResponse> responseList = usuarios.stream()
                .map(UsuarioResponse::new)
                .collect(Collectors.toList());
        return Response.ok(responseList).build();
    }

    @GET
    @RolesAllowed("user")
    @Path("/usuarios/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Requiere autenticación JWT")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Usuario usuario = authService.obtenerPorId(id);
        if (usuario != null) {
            return Response.ok(new UsuarioResponse(usuario)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @RolesAllowed("user")
    @Path("/usuarios/{id}")
    @Operation(summary = "Actualizar usuario", description = "Modifica los datos del usuario. Requiere JWT.")
    public Response actualizarUsuario(@PathParam("id") Long id, Usuario usuario) {
        Usuario actualizado = authService.actualizar(id, usuario);
        if (actualizado != null) {
            return Response.ok(new UsuarioResponse(actualizado)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @RolesAllowed("user")
    @Path("/usuarios/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario. Requiere JWT.")
    public Response eliminarUsuario(@PathParam("id") Long id) {
        boolean eliminado = authService.eliminar(id);
        return eliminado ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}