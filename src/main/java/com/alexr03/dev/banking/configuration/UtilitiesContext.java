package com.alexr03.dev.banking.configuration;

import com.alexr03.dev.banking.Utilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UtilitiesContext {
    @Bean
    @Scope("singleton")
    Utilities utilities() {
        return new Utilities();
    }
}
