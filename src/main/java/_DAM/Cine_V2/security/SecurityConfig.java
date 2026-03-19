package _DAM.Cine_V2.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // <--- 1. Activa @PreAuthorize
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter; // <--- 2. Inyectamos nuestro filtro


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Necesario para APIs REST (Stateless)
            .authorizeHttpRequests(auth -> auth
                // 🔓 PERMITIR ACCESO PÚBLICO A /auth/** (Registro y Login)
                .requestMatchers("/api/*/auth/**").permitAll()
                // 🔒 TODO LO DEMÁS: Requiere autenticación
                .anyRequest().authenticated()

            )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        ;


        return http.build();
    }
}