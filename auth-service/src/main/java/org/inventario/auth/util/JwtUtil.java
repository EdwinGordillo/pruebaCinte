package org.inventario.auth.util;

import io.smallrye.jwt.build.Jwt;

import java.time.Duration;
import java.util.Set;

public class JwtUtil {
    public static String generarToken(String username) {
        return Jwt.issuer("inventario-auth")
                .upn(username)
                .groups(Set.of("user")) // Puedes cambiar a admin, etc.
                .expiresIn(Duration.ofHours(1))
                .sign();
    }
}
