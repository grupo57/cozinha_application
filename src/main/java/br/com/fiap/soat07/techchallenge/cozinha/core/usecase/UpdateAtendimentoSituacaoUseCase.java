package br.com.fiap.soat07.techchallenge.cozinha.core.usecase;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity.Atendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.exception.PedidoJaAtendidoException;
import br.com.fiap.soat07.techchallenge.cozinha.core.gateway.AtendimentoGateway;
import br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto.PedidoDTO;

public class UpdateAtendimentoSituacaoUseCase {

    private final AtendimentoGateway atendimentoGateway;

    public UpdateAtendimentoSituacaoUseCase(final AtendimentoGateway atendimentoGateway) {
        this.atendimentoGateway = atendimentoGateway;
    }

    public Atendimento execute(Atendimento atendimento, SituacaoDoAtendimento situacao) {
        if (atendimento == null)
            throw new IllegalArgumentException();

        if (atendimento.getSituacao().isEncerrado())
            return atendimento;

        if (SituacaoDoAtendimento.RECEBIDO.equals(atendimento.getSituacao()) && SituacaoDoAtendimento.INICIADO.equals(situacao)) {
            atendimento.setSituacao(situacao);
            atendimento = atendimentoGateway.save(atendimento);
        }
        else if (SituacaoDoAtendimento.INICIADO.equals(atendimento.getSituacao()) && (SituacaoDoAtendimento.CANCELADO.equals(situacao) || SituacaoDoAtendimento.FINALIZADO.equals(situacao))) {
            atendimento.setSituacao(situacao);
            atendimento = atendimentoGateway.save(atendimento);
        }

        return atendimento;
    }

}