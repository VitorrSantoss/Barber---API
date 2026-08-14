package com.vitorsantos.barbearia_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorsantos.barbearia_api.models.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
  // Métodos de consulta da fila (contagem por barbeiro, ordenação por
  // hora de chegada etc.) entram na issue #7 — aqui é só a base.
}