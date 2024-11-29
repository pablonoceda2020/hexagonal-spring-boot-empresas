package com.hexa.challenge.infrastructure.adapters.output.persistence.mapper;

import com.hexa.challenge.domain.model.Enterprise;
import com.hexa.challenge.infrastructure.adapters.output.persistence.entity.enterprise.EnterpriseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterprisePersistenceMapper {

  EnterpriseEntity toEnterpriseEntity(Enterprise enterprise);

  Enterprise toEnterprise(EnterpriseEntity entity);

  List<Enterprise> toEnterpriseList(List<EnterpriseEntity> entityList);

}
