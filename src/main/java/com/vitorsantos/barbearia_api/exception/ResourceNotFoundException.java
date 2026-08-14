package com.vitorsantos.barbearia_api.exception;

import org.springframework.http.HttpStatus;

import com.vitorsantos.barbearia_api.enums.ErrorCode;

/**
 * Use quando um recurso buscado por ID (ou outra chave) não existe.
 * Ex: cliente, barbeiro ou agendamento não encontrado.
 * Sempre resulta em 404.
 */
public class ResourceNotFoundException extends BusinessException {

  public ResourceNotFoundException(String message, ErrorCode errorCode) {
    super(message, HttpStatus.NOT_FOUND, errorCode);
  }
}