package com.digitalhouse.CoachConnectBE.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Nuevo estilo para configuraciones específicas
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/llamadaSimple1", "/llamadaSimple2").hasRole("ESTUDIANTE")
                    .requestMatchers("/llamadaAvanzada1", "/llamadaAvanzada2").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .httpBasic();

        return http.build();
    }
}

