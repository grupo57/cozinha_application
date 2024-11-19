package br.com.fiap.soat07.techchallenge.cozinha.infra.repository.mysql;

import br.com.fiap.soat07.techchallenge.cozinha.Utils;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity.Atendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.exception.AtendimentoNotFoundException;
import br.com.fiap.soat07.techchallenge.cozinha.core.gateway.AtendimentoGateway;
import br.com.fiap.soat07.techchallenge.cozinha.infra.repository.mysql.model.AtendimentoModel;
import br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto.ProdutoDTO;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class AtendimentoRepository implements AtendimentoGateway {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Find by id
     *
     * @param id {@link Long}
     * @return {@link Optional<AtendimentoModel>}
     */
    Optional<AtendimentoModel> _findById(long id) {
        String hql = """
            SELECT a
            FROM AtendimentoModel a
            WHERE a.id = :atendimentoId
            """;

        try {
            AtendimentoModel model = entityManager.createQuery(hql, AtendimentoModel.class)
                    .setParameter("atendimentoId", id)
                    .getSingleResult();

            return Optional.of(model);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    /**
     * Find by id
     *
     * @param id {@link Long}
     * @return {@link Optional<Atendimento>}
     */
    public Optional<Atendimento> findById(long id) {
        return _findById(id)
                .map(model -> new Atendimento(
                        model.getId(), model.getIdPedido(), model.getCodigo(), model.getSituacao(), model.getDataRecebido(), model.getDataIniciado(), model.getDataConcluido(), Collections.emptySet()
                ));
    }

    @Override
    public Atendimento save(Atendimento atendimento) {
        AtendimentoModel model = null;
        if (atendimento.getId() == null) {
            model = new AtendimentoModel(atendimento.getIdPedido(), atendimento.getCodigo());
            entityManager.persist(model);
        } else {
            model = _findById(atendimento.getId()).orElseThrow(() -> new AtendimentoNotFoundException(atendimento.getId()));
            model.setCodigo(atendimento.getCodigo());
            model.setSituacao(atendimento.getSituacao());
            entityManager.merge(model);
        }

        return new Atendimento(model.getId(), model.getIdPedido(), model.getCodigo(), model.getSituacao(), model.getDataRecebido(), model.getDataIniciado(), model.getDataConcluido(), Collections.emptySet());
    }

    @Override
    public Collection<Atendimento> find(SituacaoDoAtendimento situacao, int pageNumber, int pageSize) {
        String hql = """
            SELECT a
            FROM AtendimentoModel a
            WHERE 1 = 1
              AND a.situacao = :SITUACAO
            """;
        pageNumber = Math.max(pageNumber, 1);
        pageSize = Math.max(pageSize, 1);
        int firstResult = (pageNumber - 1) * pageSize;

        Query<AtendimentoModel> query = entityManager.unwrap(Session.class).createQuery(hql, AtendimentoModel.class);
        List<AtendimentoModel> result = (List<AtendimentoModel>)query
                .setParameter("situacao", situacao)
                .setFirstResult(firstResult)
                .setMaxResults(pageSize)
                .getResultList();

        return result.stream()
                .map(model -> new Atendimento(model.getId(), model.getIdPedido(), model.getCodigo(), model.getSituacao(), model.getDataRecebido(), model.getDataIniciado(), model.getDataConcluido(), Collections.emptySet()))
                .toList();
    }


    @Override
    public Collection<Atendimento> findByPedido(Long idPedido) {
        if (idPedido == null)
            return Collections.emptyList();

        String hql = """
            SELECT a
            FROM AtendimentoModel a
            WHERE 1 = 1
              AND a.idPedido = :ID_PEDIDO
            """;

        Query<AtendimentoModel> query = entityManager.unwrap(Session.class).createQuery(hql, AtendimentoModel.class);
        List<AtendimentoModel> result = (List<AtendimentoModel>)query
                .setParameter("ID_PEDIDO", idPedido)
                .getResultList();

        return result.stream()
                .map(model -> new Atendimento(model.getId(), model.getIdPedido(), model.getCodigo(), model.getSituacao(), model.getDataRecebido(), model.getDataIniciado(), model.getDataConcluido(), Collections.emptySet()))
                .toList();
    }

    @Override
    public Collection<Atendimento> findByData(LocalDate data, Set<SituacaoDoAtendimento> situacoes) {
        if (data == null)
            return Collections.emptyList();

        String hql = """
            SELECT a
            FROM AtendimentoModel a
            WHERE 1 = 1
              AND a.dataCriacao = :DATA
            """;
        if (situacoes != null && !situacoes.isEmpty()) {
            hql += " AND a.situacao IN (:SITUACAO)";
        }

        Query<AtendimentoModel> query = entityManager.unwrap(Session.class).createQuery(hql, AtendimentoModel.class);
        query.setParameter("DATA", data);

        if (situacoes != null && !situacoes.isEmpty())
            query.setParameter("SITUACAO", situacoes.stream().map(i -> i.name()).toList());

        List<AtendimentoModel> result = (List<AtendimentoModel>)query
                .getResultList();

        return result.stream()
                .map(model -> new Atendimento(model.getId(), model.getIdPedido(), model.getCodigo(), model.getSituacao(), model.getDataRecebido(), model.getDataIniciado(), model.getDataConcluido(), Collections.emptySet()))
                .toList();
    }

    public Atendimento criar(Long idPedido, String codigo, Set<ProdutoDTO> produtos) {
        AtendimentoModel model = new AtendimentoModel(idPedido, codigo);
        entityManager.persist(model);
        return findById(model.getId()).get();
    }


}