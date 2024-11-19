package br.com.fiap.soat07.techchallenge.cozinha.infra.service;

import br.com.fiap.soat07.techchallenge.cozinha.core.gateway.AtendimentoGateway;
import br.com.fiap.soat07.techchallenge.cozinha.core.usecase.CreateAtendimentoUseCase;
import br.com.fiap.soat07.techchallenge.cozinha.core.usecase.SearchAtendimentoUseCase;
import br.com.fiap.soat07.techchallenge.cozinha.core.usecase.UpdateAtendimentoSituacaoUseCase;
import org.springframework.stereotype.Component;

@Component
public class CozinhaService {

    private final CreateAtendimentoUseCase createAtendimentoUseCase;
    private final SearchAtendimentoUseCase searchAtendimentoUseCase;
    private final UpdateAtendimentoSituacaoUseCase updateAtendimentoUseCase;

    public CozinhaService(final AtendimentoGateway atendimentoGateway) {
        this.createAtendimentoUseCase = new CreateAtendimentoUseCase(atendimentoGateway);
        this.updateAtendimentoUseCase = new UpdateAtendimentoSituacaoUseCase(atendimentoGateway);
        this.searchAtendimentoUseCase = new SearchAtendimentoUseCase(atendimentoGateway);
    }


    public CreateAtendimentoUseCase getCreateAtendimentoUseCase() {
        return createAtendimentoUseCase;
    }

    public UpdateAtendimentoSituacaoUseCase getUpdateAtendimentoUseCase() {
        return updateAtendimentoUseCase;
    }

    public SearchAtendimentoUseCase getSearchAtendimentoUseCase() {
        return searchAtendimentoUseCase;
    }
}
