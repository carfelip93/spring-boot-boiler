package com.rocketicg.app.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.rocketicg.app.infrastructure")
@EnableTransactionManagement
public class ProductPersistenceConfig {
}