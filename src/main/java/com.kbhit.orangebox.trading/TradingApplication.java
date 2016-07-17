package com.kbhit.orangebox.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableEurekaClient
@Configuration
@EnableSwagger2
public class TradingApplication {

    @Bean
    public Docket tradingApplicationAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("trading")
                .apiInfo(new ApiInfoBuilder()
                        .title("Orangebox Trading Service")
                        .description("Service responsible for trading items")
                        .build())
                .select()
                .paths(regex("(/.*)"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(TradingApplication.class, args);
    }

}
