package com.vitorsantos.barbearia_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Dados que o cliente da API envia para cadastrar um Cliente.
 * Propositalmente NÃO tem id, dataCadastro nem ultimoLogin — esses campos
 * são responsabilidade do sistema, nunca devem vir do request.
 */
public record ClienteRequestDTO(

    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
        regexp = "^[0-9]{11}$",
        message = "O telefone deve conter 11 dígitos numéricos, sem espaços, parênteses ou hífen")
    String numero

) {}