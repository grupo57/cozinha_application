package br.com.fiap.soat07.techchallenge.cozinha.core.gateway;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity.Atendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto.ProdutoDTO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface AtendimentoGateway {

    /**
     * Get by id
     * @param id {@link Long}
     * @return {@link Atendimento}
     */
    Optional<Atendimento> findById(long id);

    /**
     * Get pageable
     * @param pageNumber
     * @param pageSize
     * @return {@link Collection < Atendimento >}
     */
    Collection<Atendimento> find(SituacaoDoAtendimento situacao, int pageNumber, int pageSize);

    /**
     * Salva um novo atendimento
     * @param atendimento
     * @return
     */
    Atendimento save(Atendimento atendimento);

    /**
     * Pesquisa atendimentos registrados para um determinado pedido, por padrão um pedido deve ter um único pedido, mas em algumas situações especiais um pedido pode ser dividido em mais de um atendimento
     * @param idPedido
     * @return Collection<Atendimento>
     */
    Collection<Atendimento> findByPedido(Long idPedido);

    /**
     * Pesquisa dos atendimentos por data e situação
     * @param data
     * @param situacoes
     * @return Collection<Atendimento>
     */
    Collection<Atendimento> findByData(LocalDate data, Set<SituacaoDoAtendimento> situacoes);

    /**
     * Criar um novo atendimento com base num pedido
      * @param idPedido
     * @param codigo
     * @param produtos
     * @return
     */
    Atendimento criar(Long idPedido, String codigo, Set<ProdutoDTO> produtos);
}