package com.vitorsantos.barbearia_api.dto;

import java.time.LocalDateTime;

import com.vitorsantos.barbearia_api.models.Cliente;

/**
 * Dados devolvidos pela API ao consultar/cadastrar um Cliente.
 * Existir separado da entity evita expor detalhes internos do banco
 * e permite mudar o modelo interno sem quebrar o contrato da API.
 */
public record ClienteResponseDTO(
    Long id,
    String nome,
    String numero,
    LocalDateTime dataCadastro,
    LocalDateTime ultimoLogin) {

  public static ClienteResponseDTO fromEntity(Cliente cliente) {
    return new ClienteResponseDTO(
        cliente.getId(),
        cliente.getNome(),
        cliente.getNumero(),
        cliente.getDataCadastro(),
        cliente.getUltimoLogin());
  }
}