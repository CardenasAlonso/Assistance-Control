package com.schoolmgmt.infrastructure.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean("chatbotWebClient")
    public WebClient chatbotWebClient(@Value("${services.chatbot.url:http://localhost:8086}") String url) {
        return WebClient.builder().baseUrl(url).build();
    }

    @Bean("blockchainWebClient")
    public WebClient blockchainWebClient(@Value("${services.blockchain.url:http://localhost:8087}") String url) {
        return WebClient.builder().baseUrl(url).build();
    }
}
