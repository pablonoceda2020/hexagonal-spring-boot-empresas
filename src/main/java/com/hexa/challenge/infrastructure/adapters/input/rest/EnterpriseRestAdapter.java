package com.hexa.challenge.infrastructure.adapters.input.rest;

import com.hexa.challenge.application.ports.input.EnterpriseServicePort;
import com.hexa.challenge.infrastructure.adapters.input.rest.mapper.EnterpriseRestMapper;
import com.hexa.challenge.infrastructure.adapters.input.rest.model.request.EnterpriseCreateRequest;
import com.hexa.challenge.infrastructure.adapters.input.rest.model.response.EnterpriseResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enterprises")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Enterprise")
public class EnterpriseRestAdapter {

  private static final Logger logger = LogManager.getLogger(EnterpriseRestAdapter.class);
  private final EnterpriseServicePort servicePort;
  private final EnterpriseRestMapper restMapper;

  @PostMapping("/v1/membership")
  @Operation(summary  = "This method is used to membership the enterprise.")
  public ResponseEntity<EnterpriseResponse> membership(@Valid @RequestBody EnterpriseCreateRequest request) {
    logger.debug("Debug Init POST /enterprises/v1/membership");
    logger.info("Init POST /enterprises/v1/membership");
    logger.trace("TRACE Post /enterprises/v1/membership request {}", request);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body(restMapper.toEnterpriseResponse(
                    servicePort.membership(restMapper.toEnterprise(request))));
  }

  @GetMapping("/v1/latestTransfers")
  @Operation(summary = "This method is used to obtain the list of companies that made transfers during the last month.")
  public List<EnterpriseResponse> latestTransfers() {
    logger.debug("Debug GET /enterprises/v1/latestTransfers");
    logger.info("Init GET /enterprises/v1/latestTransfers");
    logger.trace("TRACE Get /enterprises/v1/latestTransfers");
    return restMapper.toEnterpriseResponseList(servicePort.latestTransfers());
  }

  @GetMapping("/v1/latestAdditions")
  @Operation(summary = "This method is used to obtain the list of companies that entered the last month.")
  public List<EnterpriseResponse> latestAdditions() {
    logger.debug("Debug GET /enterprises/v1/latestAdditions");
    logger.info("Init GET /enterprises/v1/latestAdditions");
    logger.trace("TRACE Get /enterprises/v1/latestAdditions");
    return restMapper.toEnterpriseResponseList(servicePort.latestAdditions());
  }

}
