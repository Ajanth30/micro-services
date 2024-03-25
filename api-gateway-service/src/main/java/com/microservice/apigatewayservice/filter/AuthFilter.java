package com.microservice.apigatewayservice.filter;

import com.microservice.apigatewayservice.utils.JwtUtil;
import com.microservice.apigatewayservice.utils.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RefreshScope
public class AuthFilter implements GatewayFilter {

    @Autowired
    private RouteValidator validator;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        if(validator.isSecured.test(request)) {
            try{
                String token = Objects.requireNonNull(request.getHeaders().get("Authorization")).toString().split(" ")[1];

                if(jwtUtil.isInvalid(token)) {
                    return this.onError(exchange,"invalid token", HttpStatus.UNAUTHORIZED);
                }

            }catch (Exception ex){
                return this.onError(exchange,ex.getMessage(), HttpStatus.UNAUTHORIZED);
            }


        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

}
