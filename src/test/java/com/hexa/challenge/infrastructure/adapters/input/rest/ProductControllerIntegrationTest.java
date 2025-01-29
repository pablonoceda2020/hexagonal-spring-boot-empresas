package com.hexa.challenge.infrastructure.adapters.input.rest;

import com.hexa.challenge.ChallengeApplication;
import com.hexa.challenge.infrastructure.adapters.config.SecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = EnterpriseRestAdapter.class)
@ContextConfiguration(classes = { ChallengeApplication.class, SecurityConfiguration.class })

@AutoConfigureAddonsWebmvcResourceServerSecurity // If your web-security depends on it, setup spring-addons security
@Import({ OAuth2SecurityConfig.class })
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getEnterprises() throws Exception {
        mockMvc.perform(get("/enterprises/v1/latestAdditions"))
            .andExpect(status().isOk());
    }
}