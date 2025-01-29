package com.hexa.challenge.infrastructure.adapters.input.rest;

import com.hexa.challenge.application.service.EnterpriseService;
import com.hexa.challenge.domain.model.Enterprise;
import com.hexa.challenge.infrastructure.adapters.input.rest.model.request.EnterpriseCreateRequest;
import com.hexa.challenge.infrastructure.adapters.input.rest.model.response.EnterpriseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnterpriseRestAdapterUnitTest {

    @InjectMocks
    EnterpriseRestAdapter enterpriseRestAdapter;

    @Mock
    EnterpriseService enterpriseService;

    Enterprise enterprise;

    EnterpriseCreateRequest enterpriseCreateRequest;

    List<EnterpriseResponse> enterpriseList;

    @BeforeEach
    void setUp(){
        enterprise = Enterprise.builder()
                .razon("Test")
                .cuit("30-234000815-9")
                .build();
        enterpriseCreateRequest = EnterpriseCreateRequest.builder()
                .razon("Test")
                .cuit("30-234000815-9")
                .build();
        EnterpriseResponse response = EnterpriseResponse.builder()
                .id(1L)
                .razon("Test")
                .cuit("30-234000815-9")
                .build();

        enterpriseList = List.of(response);
    }

    @Test
    void testMembership(){
        enterprise.setId(1L);
        when(enterpriseService.membership(enterprise)).thenReturn(enterprise);
        ResponseEntity<EnterpriseResponse> responseEntity = enterpriseRestAdapter.membership(enterpriseCreateRequest);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        assertEquals(Objects.requireNonNull(responseEntity.getBody()).getId(), 1L);
    }

    @Test
    void testLatestAdditions(){
        enterprise.setId(1L);
        when(enterpriseService.membership(enterprise)).thenReturn(enterprise);
        List<EnterpriseResponse> responseList = enterpriseRestAdapter.latestAdditions();
        assertEquals(Objects.requireNonNull(responseList.get(0)).getId(), 1L);
    }

    @Test
    void testLatestTransfers(){
        enterprise.setId(1L);
        when(enterpriseService.membership(enterprise)).thenReturn(enterprise);
        List<EnterpriseResponse> responseList = enterpriseRestAdapter.latestTransfers();
        assertEquals(Objects.requireNonNull(responseList.get(0)).getId(), 1L);
    }


}
