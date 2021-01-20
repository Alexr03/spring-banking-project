package com.alexr03.dev.banking.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LoggerContext {
    @Bean(name = "es-logger")
    @Scope("singleton")
    Logger esLogger(){
        return LoggerFactory.getLogger("es-logger");
    }
}
