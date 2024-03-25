package com.microservice.apigatewayservice.config;

import com.microservice.apigatewayservice.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("BOOK-INVENTORY-SERVICE",p -> p
                        .path("/api/v1/inventory/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("http://localhost:8080"))
                .route("AUTH-SERVER",r -> r.path("/api/v1/login")
                        .uri("http://localhost:8088"))
                .build();
    }
}
