package com.schoolmgmt.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebConfig implements WebFluxConfigurer {

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        // En WebFlux, Jackson (JSON) está configurado por defecto como la prioridad máxima.
        // Aquí puedes agregar configuradores personalizados si en el futuro los necesitas.
        configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024); // Ejemplo: subir límite a 16MB
    }
}