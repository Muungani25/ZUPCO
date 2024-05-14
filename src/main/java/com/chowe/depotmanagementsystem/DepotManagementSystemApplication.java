package com.chowe.depotmanagementsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ZUPCO DEPOT MANAGEMENT", version = "1.0", description = "Depot Management"))
public class DepotManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepotManagementSystemApplication.class, args);
    }

}
