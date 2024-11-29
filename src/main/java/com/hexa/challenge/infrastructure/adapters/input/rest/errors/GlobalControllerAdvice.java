package com.hexa.challenge.infrastructure.adapters.input.rest.errors;

import com.hexa.challenge.domain.exception.EnterpriseNotFoundException;
import com.hexa.challenge.infrastructure.adapters.input.rest.model.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.hexa.challenge.infrastructure.adapters.input.rest.model.response.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

  private static final Logger logger = LogManager.getLogger(GlobalControllerAdvice.class);

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(EnterpriseNotFoundException.class)
  public ErrorResponse handleEnterpriseNotFoundException() {
    logger.warn("An ERROR NOT_FOUND");
    return ErrorResponse.builder()
        .code(ENTERPRISE_NOT_FOUND.getCode())
        .message(ENTERPRISE_NOT_FOUND.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    logger.warn("An ERROR BAD_REQUEST");
      List<String> list = new ArrayList<>();
      for (FieldError fieldError : result.getFieldErrors()) {
          String defaultMessage = fieldError.getDefaultMessage();
          list.add(defaultMessage);
      }
      return  ErrorResponse.builder()
            .code(INVALID_ENTERPRISE.getCode())
            .message(INVALID_ENTERPRISE.getMessage())
            .details(list)
            .timestamp(LocalDateTime.now())
            .build();
  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleSecurityException(Exception exception) {
    ProblemDetail errorDetail = null;

    if (exception instanceof BadCredentialsException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
      errorDetail.setProperty("description", "The username or password is incorrect");
      logger.error("Error The username or password is incorrect exception {} ", exception.getMessage());
      return errorDetail;
    }

    if (exception instanceof AccountStatusException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
      errorDetail.setProperty("description", "The account is locked");
    }

    if (exception instanceof AccessDeniedException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
      errorDetail.setProperty("description", "You are not authorized to access this resource");
    }

    if (exception instanceof SignatureException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
      errorDetail.setProperty("description", "The JWT signature is invalid");
    }

    if (exception instanceof ExpiredJwtException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
      errorDetail.setProperty("description", "The JWT token has expired");
    }

    if (errorDetail == null) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
      errorDetail.setProperty("description", "Unknown internal server error.");
    }


    logger.error("Error. Detail {} . exception {} ", errorDetail.getProperties().get("description").toString(), exception.getMessage());
    return errorDetail;
  }

}
