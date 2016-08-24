package com.kbhit.orangebox.trading.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@EnableFeignClients
@ConditionalOnProperty("eureka.client.enabled")
public class EurekaConfiguration {

}
