package com.kbhit.orangebox.trading.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.kbhit.orangebox.trading.domain.repository")
public class DatabaseConfiguration {
}
