package br.com.fiap.soat07.techchallenge.cozinha.core.usecase;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity.Atendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.gateway.AtendimentoGateway;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Component
public class SearchAtendimentoUseCase {

	private final AtendimentoGateway atendimentoGateway;


	public SearchAtendimentoUseCase(AtendimentoGateway atendimentoGateway) {
		this.atendimentoGateway = atendimentoGateway;
	}


	/**
	 * Get by id
	 * @param id {@link Long}
	 * @return {@link Optional<Atendimento>}
	 */
	public Optional<Atendimento> findById(Long id) {
		if (id == null)
			throw new IllegalArgumentException("Obrigatório informar código do atendimento");

		return atendimentoGateway.findById(id);
	}

	/**
	 * Get by Pedido
	 * @param idPedido {@link String}
	 * @return {@link Optional<Atendimento>}
	 */
	public Collection<Atendimento> findByPedido(Long idPedido) {
		if (idPedido == null)
			throw new IllegalArgumentException("Obrigatório informar o código do pedido");

		return atendimentoGateway.findByPedido(idPedido);
	}

	/**
	 * Get by Data e Situação
	 * @param data {@link LocalDate}
	 * @return {@link Collection<Atendimento>}
	 */
	public Collection<Atendimento> findByData(LocalDate data, Set<SituacaoDoAtendimento> situacoes) {
		if (data == null)
			throw new IllegalArgumentException("Obrigatório informar a data");

		return atendimentoGateway.findByData(data, situacoes);
	}

}