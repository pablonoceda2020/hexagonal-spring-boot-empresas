package com.hexa.challenge.repositoryTest;

import com.hexa.challenge.domain.model.Enterprise;

import java.util.ArrayList;
import java.util.List;

/**
 * EnterpriseRepositoryTest
 */
public class EnterpriseRepositoryTest {


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
