package com.hexa.challenge.application.ports.output;

import com.hexa.challenge.domain.model.Enterprise;

import java.util.List;

public interface EnterprisePersistencePort {

  Enterprise membership(Enterprise enterprise);
  List<Enterprise> latestTransfers();
  List<Enterprise> latestAdditions();

}
