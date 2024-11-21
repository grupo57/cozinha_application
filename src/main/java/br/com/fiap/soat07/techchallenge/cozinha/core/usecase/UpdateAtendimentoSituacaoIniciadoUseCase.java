package br.com.fiap.soat07.techchallenge.cozinha.core.usecase;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity.Atendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.gateway.AtendimentoGateway;

public class UpdateAtendimentoSituacaoIniciadoUseCase {

    private final AtendimentoGateway atendimentoGateway;

    public UpdateAtendimentoSituacaoIniciadoUseCase(final AtendimentoGateway atendimentoGateway) {
        this.atendimentoGateway = atendimentoGateway;
    }

    public Atendimento execute(Atendimento atendimento) {
        if (atendimento == null)
            throw new IllegalArgumentException();

        if (SituacaoDoAtendimento.RECEBIDO.equals(atendimento.getSituacao())) {
            atendimento.iniciado();
            atendimento = atendimentoGateway.save(atendimento);
            return atendimento;
        }
        
        if (SituacaoDoAtendimento.INICIADO.equals(atendimento.getSituacao()))
            return atendimento;

        if (SituacaoDoAtendimento.ENTREGUE.equals(atendimento.getSituacao())) {
        	throw new IllegalStateException("atendimento já finalizado");
        }
        
        if (SituacaoDoAtendimento.CANCELADO.equals(atendimento.getSituacao()))
        	throw new IllegalStateException("atendimento cancelado");
        
        return atendimento;
    }

}