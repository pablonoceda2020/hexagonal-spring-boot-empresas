package com.hexa.challenge.application.ports.input;

import com.hexa.challenge.domain.model.Enterprise;
import com.hexa.challenge.domain.exception.EnterpriseNotFoundException;

import java.util.List;

public interface EnterpriseServicePort {

  Enterprise membership(Enterprise enterprise) throws EnterpriseNotFoundException;
  List<Enterprise> latestTransfers();
  List<Enterprise> latestAdditions();

}
