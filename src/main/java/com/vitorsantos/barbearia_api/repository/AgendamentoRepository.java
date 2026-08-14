package com.vitorsantos.barbearia_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitorsantos.barbearia_api.enums.StatusAgendamento;
import com.vitorsantos.barbearia_api.models.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

  /**
   * Total de agendamentos de um barbeiro num determinado status.
   * Mais leve que trazer a lista inteira quando só se quer o número
   * (ex: um card de dashboard que mostra só "5 na fila").
   */
  long countByBarbeiroIdAndStatusAgendamento(Long barbeiroId, StatusAgendamento status);

  /**
   * Lista ordenada pela ordem de chegada — é essa ordem que define
   * a posição de cada cliente na fila (índice 0 = próximo a ser atendido).
   */
  List<Agendamento> findByBarbeiroIdAndStatusAgendamentoOrderByHoraChegadaAsc(
      Long barbeiroId, StatusAgendamento status);
}