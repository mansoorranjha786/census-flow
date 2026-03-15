package com.postwork.cursorflow;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "CensusFlow API",
        version = "1.0.0",
        description = "Standardized Group Benefit Data API for CoverPoint Brokers",
        contact = @Contact(name = "CoverPoint Brokers",
                email = "support@postwork.com")
))
public class CensusFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(CensusFlowApplication.class, args);
    }

}
