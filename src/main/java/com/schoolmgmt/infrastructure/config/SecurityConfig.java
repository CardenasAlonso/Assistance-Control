package com.schoolmgmt.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable) // <-- APAGAMOS EL FORMULARIO
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // <-- APAGAMOS EL LOGIN BÁSICO
            .authorizeExchange(exchanges -> exchanges
                .anyExchange().permitAll() 
            );
        return http.build();
    }
}