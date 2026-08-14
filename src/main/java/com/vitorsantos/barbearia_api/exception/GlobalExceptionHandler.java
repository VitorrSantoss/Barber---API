package com.vitorsantos.barbearia_api.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.vitorsantos.barbearia_api.enums.ErrorCode;

import lombok.extern.slf4j.Slf4j;

/**
 * Handler global de exceções. Todo erro lançado por qualquer controller
 * passa por aqui e sai no mesmo formato (ErrorResponseDTO).
 *
 * Ordem de prioridade dos handlers (Spring escolhe o mais específico):
 * 1. BusinessException (e subclasses) -> status definido pela própria exceção
 * 2. MethodArgumentNotValidException (@Valid) -> 400
 * 3. DataIntegrityViolationException (banco) -> 409
 * 4. Exception (fallback, bug real) -> 500
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException ex, WebRequest request) {
    log.warn("Erro de negócio [{}]: {}", ex.getErrorCode(), ex.getMessage());

    ErrorResponseDTO body = ErrorResponseDTO.builder()
        .status(ex.getStatus().value())
        .codigo(ex.getErrorCode())
        .mensagem(ex.getMessage())
        .timestamp(LocalDateTime.now())
        .path(extrairPath(request))
        .build();

    return ResponseEntity.status(ex.getStatus()).body(body);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex,
      WebRequest request) {
    List<String> erros = ex.getBindingResult().getFieldErrors().stream()
        .map(this::formatarErroDeCampo)
        .collect(Collectors.toList());

    log.warn("Erro de validação: {}", erros);

    ErrorResponseDTO body = ErrorResponseDTO.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .codigo(ErrorCode.ERRO_VALIDACAO)
        .mensagem("Um ou mais campos estão inválidos")
        .timestamp(LocalDateTime.now())
        .path(extrairPath(request))
        .erros(erros)
        .build();

    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex,
      WebRequest request) {
    log.warn("Violação de integridade no banco: {}", ex.getMostSpecificCause().getMessage());

    ErrorResponseDTO body = ErrorResponseDTO.builder()
        .status(HttpStatus.CONFLICT.value())
        .codigo(ErrorCode.ERRO_VALIDACAO)
        .mensagem("Já existe um registro com esses dados (violação de restrição única)")
        .timestamp(LocalDateTime.now())
        .path(extrairPath(request))
        .build();

    return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, WebRequest request) {
    log.error("Erro inesperado", ex);

    ErrorResponseDTO body = ErrorResponseDTO.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .codigo(ErrorCode.ERRO_INTERNO)
        .mensagem("Ocorreu um erro interno. Tente novamente mais tarde.")
        .timestamp(LocalDateTime.now())
        .path(extrairPath(request))
        .build();

    return ResponseEntity.internalServerError().body(body);
  }

  private String formatarErroDeCampo(FieldError erro) {
    return erro.getField() + ": " + erro.getDefaultMessage();
  }

  private String extrairPath(WebRequest request) {
    return request.getDescription(false).replace("uri=", "");
  }
}