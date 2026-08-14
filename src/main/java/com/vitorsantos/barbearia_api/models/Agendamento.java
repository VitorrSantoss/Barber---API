package com.vitorsantos.barbearia_api.models;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import com.vitorsantos.barbearia_api.enums.StatusAgendamento;
import com.vitorsantos.barbearia_api.enums.ErrorCode;
import com.vitorsantos.barbearia_api.exception.ValidacaoException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_agendamentos")
public class Agendamento {

  /**
   * Mapa de transições permitidas: de qual status, para quais status
   * é possível ir. Isso concentra a regra num único lugar — se amanhã
   * o fluxo mudar, só se mexe aqui.
   */
  private static final Map<StatusAgendamento, Set<StatusAgendamento>> TRANSICOES_PERMITIDAS = Map.of(
      StatusAgendamento.AGENDADO, EnumSet.of(StatusAgendamento.AGUARDANDO, StatusAgendamento.CANCELADO),
      StatusAgendamento.AGUARDANDO, EnumSet.of(StatusAgendamento.EM_ATENDIMENTO, StatusAgendamento.CANCELADO),
      StatusAgendamento.EM_ATENDIMENTO, EnumSet.of(StatusAgendamento.FINALIZADO),
      StatusAgendamento.FINALIZADO, EnumSet.noneOf(StatusAgendamento.class),
      StatusAgendamento.CANCELADO, EnumSet.noneOf(StatusAgendamento.class));

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cliente_id", nullable = false)
  private Cliente cliente;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "barbeiro_id", nullable = false)
  private Barbeiro barbeiro;

  @Column(name = "data_hora", nullable = false)
  private LocalDateTime dataHora;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_agendamento", nullable = false, length = 20)
  private StatusAgendamento statusAgendamento;

  /**
   * Timestamp de quando o cliente chegou fisicamente na barbearia
   * (entrou na fila). Fica null até o status virar AGUARDANDO.
   */
  @Column(name = "hora_chegada")
  private LocalDateTime horaChegada;

  /**
   * Único jeito "correto" de mudar o status de um agendamento — valida
   * a transição antes de aplicar. Preferir isso a um `setStatusAgendamento`
   * direto, que não tem essa checagem.
   *
   * @throws ValidacaoException se a transição não for permitida
   */
  public void mudarStatus(StatusAgendamento novoStatus) {
    Set<StatusAgendamento> permitidos = TRANSICOES_PERMITIDAS.get(this.statusAgendamento);

    if (permitidos == null || !permitidos.contains(novoStatus)) {
      throw new ValidacaoException(
          "Não é possível mudar o agendamento de %s para %s"
              .formatted(this.statusAgendamento, novoStatus),
          ErrorCode.TRANSICAO_STATUS_INVALIDA);
    }

    if (novoStatus == StatusAgendamento.AGUARDANDO) {
      this.horaChegada = LocalDateTime.now();
    }

    this.statusAgendamento = novoStatus;
  }
}