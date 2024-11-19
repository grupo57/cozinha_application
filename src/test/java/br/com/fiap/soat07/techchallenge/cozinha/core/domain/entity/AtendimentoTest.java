package br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.TipoProdutoEnum;
import br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto.ProdutoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AtendimentoTest {

    private Atendimento atendimento;

    private Long id = 1L;
    private Long idPedido = 123L;
    private String codigo = "ABC123";
    private LocalDateTime inicio = LocalDateTime.now();
    private LocalDateTime preparo = LocalDateTime.now().minusMinutes(30);
    private LocalDateTime concluido = LocalDateTime.now();
    private Set<ProdutoDTO> produtos = new HashSet(List.of(
            new ProdutoDTO(1L, "nome1", "codigo1",BigDecimal.valueOf(10), TipoProdutoEnum.ACOMPANHAMENTO),
            new ProdutoDTO(2L, "nome2", "codigo2", BigDecimal.valueOf(20), TipoProdutoEnum.LANCHE)
            ));

    @BeforeEach
    void setUp() {
        // Inicializando o objeto Atendimento com dados de teste
        atendimento = new Atendimento(id, idPedido, codigo, SituacaoDoAtendimento.RECEBIDO, inicio, preparo, concluido, produtos);
    }

    @Test
    void testAtendimentoConstrutorPadrao() {
        // Criando um Atendimento com o construtor padrão
        Atendimento atendimentoPadrao = new Atendimento();

        // Verificando se o campo 'inicio' é definido para a data e hora atual
        assertThat(atendimentoPadrao.getInicio()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    void testAtendimentoConstrutor() {
        // Verificando se o construtor inicializa corretamente os campos
        assertThat(atendimento.getId()).isEqualTo(id);
        assertThat(atendimento.getIdPedido()).isEqualTo(idPedido);
        assertThat(atendimento.getCodigo()).isEqualTo(codigo);
        assertThat(atendimento.getSituacao()).isEqualTo(SituacaoDoAtendimento.RECEBIDO);
        assertThat(atendimento.getInicio()).isEqualTo(inicio);
        assertThat(atendimento.getPreparado()).isEqualTo(preparo);
        assertThat(atendimento.getConcluido()).isEqualTo(concluido);
    }

    @Test
    void testRecebido() {
        // Criando um Atendimento usando o método 'recebido'
        Atendimento atendimentoRecebido = Atendimento.recebido(id, idPedido, codigo, produtos);

        // Verificando se o atendimento foi criado com a situação 'RECEBIDO'
        assertThat(atendimentoRecebido.getSituacao()).isEqualTo(SituacaoDoAtendimento.RECEBIDO);
        assertThat(atendimentoRecebido.getId()).isEqualTo(id);
        assertThat(atendimentoRecebido.getIdPedido()).isEqualTo(idPedido);
        assertThat(atendimentoRecebido.getCodigo()).isEqualTo(codigo);
        assertThat(atendimentoRecebido.getInicio()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    void testGettersSetters() {
        // Testando o getter e setter para 'situacao'
        atendimento.setSituacao(SituacaoDoAtendimento.FINALIZADO);
        assertThat(atendimento.getSituacao()).isEqualTo(SituacaoDoAtendimento.FINALIZADO);

        // Testando o setter para 'preparo'
        LocalDateTime novoPreparo = LocalDateTime.now().minusMinutes(10);
        atendimento.setPreparo(novoPreparo);
        assertThat(atendimento.getPreparado()).isEqualTo(novoPreparo);

        // Testando o setter para 'concluido'
        LocalDateTime novoConcluido = LocalDateTime.now();
        atendimento.setConcluido(novoConcluido);
        assertThat(atendimento.getConcluido()).isEqualTo(novoConcluido);
    }

    @Test
    void testToString() {
        // Verificando se o método 'toString' retorna a string formatada corretamente
        String expectedString = "Atendimento{id=" + id + ", pedido='" + idPedido + "', codigo='" + codigo + "', situacao='RECEBIDO'}";
        assertThat(atendimento.toString()).isEqualTo(expectedString);
    }
}
