package com.hexa.challenge.infrastructure.adapters.input.rest;

import com.hexa.challenge.application.ports.input.EnterpriseServicePort;
import com.hexa.challenge.domain.model.Enterprise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnterpriseRestAdapter.class)
@Import(EnterpriseRestAdapterTestConfiguration.class)
@ExtendWith(MockitoExtension.class)
class EnterpriseRestAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnterpriseServicePort servicePort;

    @BeforeEach
    void setUp(){
        Enterprise enterprise;

        enterprise = Enterprise.builder()
                .id(1L)
                .razon("Test")
                .cuit("30-234000815-9")
                .build();

        when((servicePort.membership(enterprise))).thenReturn(enterprise);
    }

    @Test
    void getAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/enterprises/v1/membership")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }


}
