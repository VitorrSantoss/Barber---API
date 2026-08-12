package com.vitorsantos.barbearia_api.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "tb_clientes")
public class Cliente {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY )
  @Column(name = "id")
  private Long id;

  @Column(name = "nome", nullable = false, length = 100 )
  private String nome;
  
  @Column(name = "numeroTelefone", nullable = false, unique = true )
  private String numero;

  @CreationTimestamp // utilizado para preencher automaticamente a data e hora exatas de criação de um registro no banco de dados.
  @Column(name = "dataCadastro")
  private LocalDateTime dataCadastro;

  @Column(name = "ultimoLogin")
  private LocalDateTime ultimoLogin;

}
