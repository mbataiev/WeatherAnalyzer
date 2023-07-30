package com.app.weather;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Weather REST API Documentation",
                description = "Weather REST API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Maksym",
                        email = "max.bataiev@gmail.com",
                        url = "https://github.com/mbataiev"
                )
        )
)
@Slf4j
public class WeatherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherServiceApplication.class, args);
        log.info("Info root");
        log.debug("Debug root");
        log.warn("Warn root");
        log.error("Error root");
        log.trace("Trace root");
    }

}
