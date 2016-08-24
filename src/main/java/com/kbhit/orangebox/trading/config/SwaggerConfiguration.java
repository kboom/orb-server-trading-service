package com.kbhit.orangebox.trading.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket storageApplicationAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("trading")
                .apiInfo(new ApiInfoBuilder()
                        .title("Orangebox trading service")
                        .description("Service responsible for trading")
                        .build())
                .select()
                .paths(regex("(/.*)"))
                .build();
    }

}
