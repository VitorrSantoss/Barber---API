package com.vitorsantos.barbearia_api.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Exceção base para todo erro de REGRA DE NEGÓCIO do sistema.
 * Nunca deve ser lançada diretamente — use uma das subclasses
 * (ResourceNotFoundException, ConflitoDeRegraException, ValidacaoException)
 * para deixar claro, só pelo nome, qual é o problema.
 */
@Getter
public abstract class BusinessException extends RuntimeException {

  private final HttpStatus status;
  private final ErrorCode errorCode;

  protected BusinessException(String message, HttpStatus status, ErrorCode errorCode) {
    super(message);
    this.status = status;
    this.errorCode = errorCode;
  }
}