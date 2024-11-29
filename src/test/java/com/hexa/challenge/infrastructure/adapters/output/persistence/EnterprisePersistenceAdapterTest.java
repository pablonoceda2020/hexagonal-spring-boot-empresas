package com.hexa.challenge.infrastructure.adapters.output.persistence;

import com.hexa.challenge.domain.model.Enterprise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EnterprisePersistenceAdapterTest {


    @Mock
    private EnterprisePersistenceAdapter enterpriseRepository;

    @Test
    void givenEnterprisePersistenceAdapter_whenAddEnterpriseEntity_thenOK() {
        // given
        String name = "test";

        Mockito.when(enterpriseRepository.membership(any())).thenReturn(createEnterprise());

        // when
        Enterprise found = enterpriseRepository.membership(createEnterprise());

        // then
        // AssertJ
        assertThat(found.getRazon()).isEqualTo(name);
    }

    @Test
    void givenEnterprisePersistenceAdapter_whenLatestAdditionsEnterpriseEntity_thenOK() {
        // given
        String name = "test";

        Mockito.when(enterpriseRepository.latestAdditions()).thenReturn(listEnterprises());

        List<Enterprise> enterpriseList = enterpriseRepository.latestAdditions();

        // then
        // AssertJ
        assertThat(enterpriseList.get(0).getRazon()).isEqualTo(name);
    }

    @Test
    void givenEnterprisePersistenceAdapter_whenLatestTransfersEnterpriseEntity_thenOK() {
        // given
        String name = "test";

        Mockito.when(enterpriseRepository.latestTransfers()).thenReturn(listEnterprises());

        List<Enterprise> enterpriseList = enterpriseRepository.latestTransfers();

        // then
        // AssertJ
        assertThat(enterpriseList.get(0).getRazon()).isEqualTo(name);
    }

    public static Enterprise createEnterprise() {

        return Enterprise.builder()
                .razon("test")
                .cuit("302399988801")
                .build();
    }

    public static List<Enterprise> listEnterprises() {
        List<Enterprise> enterprises = new ArrayList<>();
        enterprises.add(createEnterprise());
        return enterprises;
    }
}
