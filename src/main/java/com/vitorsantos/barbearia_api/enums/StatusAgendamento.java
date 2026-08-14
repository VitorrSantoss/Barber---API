package com.vitorsantos.barbearia_api.enums;

/**
 * Ciclo de vida de um agendamento. A ordem "natural" do fluxo é:
 *
 *   AGENDADO -> AGUARDANDO -> EM_ATENDIMENTO -> FINALIZADO
 *
 * CANCELADO pode acontecer a partir de AGENDADO ou AGUARDANDO.
 * As transições permitidas são validadas em Agendamento#mudarStatus,
 * não aqui — o enum só declara os estados possíveis.
 */
public enum StatusAgendamento {

  /** Cliente marcou horário, mas ainda não chegou na barbearia. */
  AGENDADO,

  /** Cliente chegou e está fisicamente na fila esperando o barbeiro. */
  AGUARDANDO,

  /** Barbeiro está atendendo o cliente agora. */
  EM_ATENDIMENTO,

  /** Atendimento concluído. Estado final. */
  FINALIZADO,

  /** Agendamento cancelado, por qualquer motivo. Estado final. */
  CANCELADO
}