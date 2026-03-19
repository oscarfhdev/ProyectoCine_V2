package _DAM.Cine_V2.security;

import _DAM.Cine_V2.modelo.Rol;
import _DAM.Cine_V2.modelo.Usuario;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret; // "MiClave..." (String)

    @Value("${jwt.expiration}")
    private long expirationTime;

    private SecretKey key; // Objeto Criptográfico Real

    // ⚡ SE EJECUTA DESPUÉS DEL CONSTRUCTOR
    @PostConstruct
    public void init() {
        // Transformamos la password de texto a una llave HMAC-SHA compatible
        // ¿Por qué aquí? Porque en el constructor 'secret' aún es null.
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("role", usuario.getRoles())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false; // Firma inválida o expirado
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}