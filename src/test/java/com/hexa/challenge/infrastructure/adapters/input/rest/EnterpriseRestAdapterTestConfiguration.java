package com.hexa.challenge.infrastructure.adapters.input.rest;

import com.hexa.challenge.ChallengeApplication;
import com.hexa.challenge.application.service.EnterpriseService;
import com.hexa.challenge.infrastructure.adapters.config.SecurityConfiguration;
import com.hexa.challenge.infrastructure.adapters.output.persistence.EnterprisePersistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

@TestConfiguration
@ContextConfiguration(classes = { ChallengeApplication.class, SecurityConfiguration.class })
public class EnterpriseRestAdapterTestConfiguration {
    @Autowired
    private EnterprisePersistenceAdapter enterprisePersistenceAdapter;
    @Bean
    public EnterpriseService bookService(){
        return new EnterpriseService(this.enterprisePersistenceAdapter);
    }
}
