package org.inventario.auth.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.inventario.auth.dto.LoginRequest;
import org.inventario.auth.dto.LoginResponse;
import org.inventario.auth.service.AuthService;
import org.inventario.auth.util.JwtUtil;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Autenticación", description = "Operaciones de login de usuario")
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
}