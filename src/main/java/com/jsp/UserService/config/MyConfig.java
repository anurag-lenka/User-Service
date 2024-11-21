package com.jsp.UserService.config;

import com.zaxxer.hikari.HikariDataSource;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class MyConfig {

    @Bean
    HikariDataSource getDataSource(){
       HikariDataSource hikariDataSource= new HikariDataSource();
       hikariDataSource.setUsername("root");
       hikariDataSource.setPassword("Alenka@123");
       hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/experiment");
       hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
       return hikariDataSource;
    }

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        return CircuitBreakerRegistry.of(CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(10)
                .build());
    }

}
