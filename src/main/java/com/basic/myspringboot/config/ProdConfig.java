package com.basic.myspringboot.config;

import com.basic.myspringboot.dto.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProdConfig {

    @Profile("prod")
    @Bean
    public Customer customer() {
        return Customer.builder() //CustomerBuilder inner class
                .name("운영모드")
                .age(50)
                .build();
    }

}
