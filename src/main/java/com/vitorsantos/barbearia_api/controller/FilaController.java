package com.vitorsantos.barbearia_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorsantos.barbearia_api.dto.ErrorResponseDTO;
import com.vitorsantos.barbearia_api.dto.FilaResponseDTO;
import com.vitorsantos.barbearia_api.service.FilaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Fila", description = "Consulta da fila de espera dos barbeiros (versão por polling)")
@RestController
@RequestMapping("/barbeiros")
@RequiredArgsConstructor
public class FilaController {

  private final FilaService filaService;

  @Operation(
      summary = "Consulta a fila de espera de um barbeiro específico",
      description = "Retorna os clientes com status AGUARDANDO para esse barbeiro, "
          + "ordenados por ordem de chegada. Pensado para ser chamado em polling "
          + "pelo front (ex: a cada 5-10s) até a issue #9 trazer atualização em tempo real via WebSocket.")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Fila retornada com sucesso",
          content = @Content(schema = @Schema(implementation = FilaResponseDTO.class))),
      @ApiResponse(
          responseCode = "404",
          description = "Barbeiro não encontrado",
          content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
  })
  @GetMapping("/{id}/fila")
  public FilaResponseDTO consultarFilaPorBarbeiro(@PathVariable Long id) {
    return filaService.consultarFilaPorBarbeiro(id);
  }

  @Operation(
      summary = "Consulta a fila de espera de todos os barbeiros de uma vez",
      description = "Pensado para um painel geral da barbearia, mostrando a fila de todo mundo ao mesmo tempo.")
  @ApiResponse(responseCode = "200", description = "Lista de filas retornada com sucesso")
  @GetMapping("/fila")
  public List<FilaResponseDTO> consultarFilaDeTodosBarbeiros() {
    return filaService.consultarFilaDeTodosBarbeiros();
  }

}