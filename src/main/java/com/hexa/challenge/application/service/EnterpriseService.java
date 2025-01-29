package com.hexa.challenge.application.service;

import com.hexa.challenge.application.ports.input.EnterpriseServicePort;
import com.hexa.challenge.application.ports.output.EnterprisePersistencePort;
import com.hexa.challenge.domain.exception.CUITNotFoundException;
import com.hexa.challenge.domain.exception.EnterpriseNotFoundException;
import com.hexa.challenge.domain.model.Enterprise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnterpriseService implements EnterpriseServicePort {


  private final EnterprisePersistencePort persistencePort;

  @Override
  public Enterprise membership(Enterprise enterprise) throws CUITNotFoundException {
    if (!isValidCUITCUIL(enterprise.getCuit()))
      throw new CUITNotFoundException();
    return persistencePort.membership(enterprise);
  }

  @Override
  public List<Enterprise> latestTransfers() {
    return persistencePort.latestTransfers();
  }

  @Override
  public List<Enterprise> latestAdditions() { return persistencePort.latestAdditions(); }


  private boolean isValidCUITCUIL(String cuit){

      if (cuit.length() != 13) return false;

      boolean rv = false;
      int resultado = 0;
      String cuit_nro = cuit.replace("-", "");
      String codes = "6789456789";
      int verificador = Character.getNumericValue(cuit_nro.charAt(cuit_nro.length() - 1));
      int x = 0;

      while (x < 10)
      {
        int digitoValidador = Integer.parseInt(codes.substring(x, x+1));
        int digito = Integer.parseInt(cuit_nro.substring(x, x+1));
        int digitoValidacion = digitoValidador * digito;
        resultado += digitoValidacion;
        x++;
      }
      resultado = resultado % 11;
      rv = (resultado == verificador);

    return rv;
  }


}
