package com.vitorsantos.barbearia_api.exception;

import org.springframework.http.HttpStatus;

import com.vitorsantos.barbearia_api.enums.ErrorCode;

/**
 * Use quando a operação conflita com um estado já existente.
 * Ex: telefone já cadastrado, horário já ocupado por outro agendamento.
 * Sempre resulta em 409.
 */
public class ConflitoDeRegraException extends BusinessException {

  public ConflitoDeRegraException(String message, ErrorCode errorCode) {
    super(message, HttpStatus.CONFLICT, errorCode);
  }
}