package com.example;

import javax.ws.rs.ApplicationPath;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "Number API",
        description = "This API allows to generate all sorts of ISBN numbers",
        version = "1.0"
    ),
    tags = {
        @Tag(
            name = "api",
            description = "PublicAPI that can be used by anybody"
        ),
        @Tag(
            name = "numbers",
            description = "Anybody interested in ISBN numbers"
        )
    }
)
public class NumberApplication {
    
}
