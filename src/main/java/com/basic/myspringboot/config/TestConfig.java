package com.basic.myspringboot.config;

import com.basic.myspringboot.dto.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class TestConfig {

    @Profile("test")
    @Bean
    public Customer customer() {
        return Customer.builder()
                .name("테스트모드")
                .age(10)
                .build();
    }

}
