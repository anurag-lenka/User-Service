package com.jsp.UserService.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
