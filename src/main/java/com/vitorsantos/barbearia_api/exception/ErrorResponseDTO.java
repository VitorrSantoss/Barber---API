package com.vitorsantos.barbearia_api.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * Formato ÚNICO de corpo de erro devolvido por qualquer endpoint da API.
 * O campo "erros" só é preenchido quando há falha de validação em
 * múltiplos campos (ex: @Valid no corpo da requisição).
 */
@Getter
@Builder
public class ErrorResponseDTO {

  private int status;
  private ErrorCode codigo;
  private String mensagem;
  private LocalDateTime timestamp;
  private String path;
  private List<String> erros;
}