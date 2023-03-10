package com.coder.ws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Configuration
public class SpringFoxConfig {

    private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<>(List.of("application/json"));

    @Value("${controller.properties.base-path}")
    private String uriBasePattern;

    private final SwaggerProperties swaggerProperties;

    public SpringFoxConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket api() {
        String regexUri = "/" + this.uriBasePattern + ".*";
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(DEFAULT_PRODUCES_CONSUMES)
                .consumes(DEFAULT_PRODUCES_CONSUMES)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex(regexUri))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                this.swaggerProperties.getProjectName(),
                this.swaggerProperties.getProjectShortDescription(),
                this.swaggerProperties.getProjectTosMsg(),
                this.swaggerProperties.getProjectTosLink(),
                new Contact(
                        this.swaggerProperties.getDeveloperName(),
                        this.swaggerProperties.getOrganizationUrl(),
                        this.swaggerProperties.getDeveloperMail()),
                this.swaggerProperties.getProjectLicenceMsg(),
                this.swaggerProperties.getProjectLicenceLink(),
                Collections.emptyList()
        );
    }

}
