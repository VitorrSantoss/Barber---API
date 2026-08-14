package com.vitorsantos.barbearia_api.dto;

import java.time.LocalDateTime;

/**
 * Representa um cliente dentro da fila de um barbeiro específico,
 * já com a posição calculada (1 = próximo a ser atendido).
 */
public record ClienteNaFilaDTO(
    Long clienteId,
    String nomeCliente,
    int posicao,
    LocalDateTime horaChegada) {
}