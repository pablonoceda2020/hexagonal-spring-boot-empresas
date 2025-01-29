package com.hexa.challenge.infrastructure.adapters.output.persistence;

import com.hexa.challenge.domain.model.Enterprise;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnterprisePersistenceAdapterTest {

    @Mock
    private EnterprisePersistenceAdapter enterpriseRepository;

    static Enterprise enterprise;
    static List<Enterprise> enterprises;

    @BeforeAll
    static void setUp() {
        enterprise = Enterprise.builder()
                .razon("test")
                .cuit("30-239998880-1")
                .build();

        enterprises = new ArrayList<>();
        enterprises.add(enterprise);
    }

    @Test
    void givenEnterprisePersistenceAdapter_whenAddEnterpriseEntity_thenOK() {
        String name = "test";
        when(enterpriseRepository.membership(any())).thenReturn(enterprise);
        Enterprise found = enterpriseRepository.membership(enterprise);
        assertThat(found.getRazon()).isEqualTo(name);
    }

    @Test
    void givenEnterprisePersistenceAdapter_whenLatestAdditionsEnterpriseEntity_thenOK() {
        String name = "test";
        when(enterpriseRepository.latestAdditions()).thenReturn(enterprises);
        List<Enterprise> enterpriseList = enterpriseRepository.latestAdditions();
        assertThat(enterpriseList.get(0).getRazon()).isEqualTo(name);
    }

    @Test
    void givenEnterprisePersistenceAdapter_whenLatestTransfersEnterpriseEntity_thenOK() {
        String name = "test";
        when(enterpriseRepository.latestTransfers()).thenReturn(enterprises);
        List<Enterprise> enterpriseList = enterpriseRepository.latestTransfers();
        assertThat(enterpriseList.get(0).getRazon()).isEqualTo(name);
    }

}
