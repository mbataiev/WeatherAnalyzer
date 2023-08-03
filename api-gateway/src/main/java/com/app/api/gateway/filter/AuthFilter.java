package com.app.api.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final RouteValidator validator;
    private final RestTemplate restTemplate;

    public AuthFilter(RouteValidator validator, RestTemplate restTemplate) {
        super(Config.class);
        this.validator = validator;
        this.restTemplate = restTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    // TODO: 02.08.2023 add microservice name //AUTH-SERVICE
                    restTemplate.getForObject("http://localhost:9191/api/v1/auth/validate?token=" + authHeader, String.class);
                }catch (Exception e){
                    throw new RuntimeException("Error while rest call to auth service");
                }

            }

            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }
}
