package com.vitorsantos.barbearia_api.dto;

import java.util.List;

public record FilaResponseDTO(
    Long barbeiroId,
    int totalNaFila,
    List<ClienteNaFilaDTO> clientes) {
}