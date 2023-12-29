package com.customer.portfolio.customerportfoliospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.CorsRegistry;

import com.customer.portfolio.customerportfoliospring.domain.CustomerRepository;
import com.customer.portfolio.customerportfoliospring.domain.CustomerService;

import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableR2dbcAuditing // createdAt and UpdatedAt
@EnableAsync
public class CustomerServiceConfig implements WebFluxConfigurer {
    @Bean
    CustomerService customerService(CustomerRepository customerRepository) {
        return new CustomerService(customerRepository);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }
    
}
