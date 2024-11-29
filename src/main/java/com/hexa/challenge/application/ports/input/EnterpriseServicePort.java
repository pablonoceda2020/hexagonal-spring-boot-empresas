package com.hexa.challenge.application.ports.input;

import com.hexa.challenge.domain.model.Enterprise;

import java.util.List;

public interface EnterpriseServicePort {

  Enterprise membership(Enterprise enterprise);
  List<Enterprise> latestTransfers();
  List<Enterprise> latestAdditions();

}
