package com.microservice.apigatewayservice.utils;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> unProtectedRoutes=List.of("/login");
    public Predicate<ServerHttpRequest> isSecured=request->unProtectedRoutes.stream()
            .noneMatch(uri->request.getURI().getPath().contains(uri));
}
