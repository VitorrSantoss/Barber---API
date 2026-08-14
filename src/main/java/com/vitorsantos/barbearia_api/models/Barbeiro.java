package com.vitorsantos.barbearia_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_barbeiros")
public class Barbeiro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "nome", nullable = false, length = 100)
  private String nome;

  /**
   * Controla se o barbeiro está ativo/trabalhando. Um barbeiro inativo não
   * deve aparecer nas telas de fila nem receber novos agendamentos.
   * Default true — todo barbeiro cadastrado começa ativo.
   */
  @Column(name = "ativo", nullable = false)
  private boolean ativo = true;
}