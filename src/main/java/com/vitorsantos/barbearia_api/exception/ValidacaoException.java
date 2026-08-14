package com.vitorsantos.barbearia_api.exception;

import org.springframework.http.HttpStatus;

import com.vitorsantos.barbearia_api.enums.ErrorCode;

/**
 * Use para regras de validação que o Bean Validation (@Valid, @NotBlank etc.)
 * não cobre — validações que dependem de lógica de negócio.
 * Ex: transição de status de agendamento inválida (FINALIZADO -> AGUARDANDO).
 * Sempre resulta em 400.
 */
public class ValidacaoException extends BusinessException {

  public ValidacaoException(String message, ErrorCode errorCode) {
    super(message, HttpStatus.BAD_REQUEST, errorCode);
  }
}