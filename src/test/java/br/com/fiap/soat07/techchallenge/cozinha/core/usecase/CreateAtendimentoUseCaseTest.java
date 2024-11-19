package br.com.fiap.soat07.techchallenge.cozinha.core.usecase;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity.Atendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.TipoProdutoEnum;
import br.com.fiap.soat07.techchallenge.cozinha.core.exception.PedidoJaAtendidoException;
import br.com.fiap.soat07.techchallenge.cozinha.core.gateway.AtendimentoGateway;
import br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto.PedidoDTO;
import br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto.ProdutoDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateAtendimentoUseCaseTest {

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @InjectMocks
    private CreateAtendimentoUseCase createAtendimentoUseCase;

    private PedidoDTO pedidoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(1L);
        pedidoDTO.setCodigo("123");
        pedidoDTO.setCliente("Cliente X");
        pedidoDTO.setProdutos(new HashSet(List.of(
                new ProdutoDTO(1L, "nome1", "codigo1", BigDecimal.valueOf(10), TipoProdutoEnum.ACOMPANHAMENTO),
                new ProdutoDTO(2L, "nome2", "codigo2", BigDecimal.valueOf(20), TipoProdutoEnum.LANCHE)
                )));
    }

    @Test
    void givenNullPedidoDTO_whenExecute_thenThrowsIllegalArgumentException() {
        // Ação & Validação
        assertThrows(IllegalArgumentException.class, () -> createAtendimentoUseCase.execute(null));
    }

    @Test
    void givenEmptyCodigo_whenExecute_thenThrowsIllegalArgumentException() {
        // Preparando o pedido com código vazio
        pedidoDTO.setCodigo("");

        // Ação & Validação
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> createAtendimentoUseCase.execute(pedidoDTO));
        assertEquals("Obrigatório informar o codigo do pedido", exception.getMessage());
    }

    @Test
    void givenPedidoJaAtendido_whenExecute_thenThrowsPedidoJaAtendidoException() {
        // Configuração do comportamento do mock
        LocalDateTime inicio = LocalDateTime.now();
        when(atendimentoGateway.findByPedido(pedidoDTO.getId())).thenReturn(List.of(
                Atendimento.recebido(1L, pedidoDTO.getId(), pedidoDTO.getCodigo(), pedidoDTO.getProdutos())));

        // Ação & Validação
        PedidoJaAtendidoException exception = assertThrows(PedidoJaAtendidoException.class, () -> createAtendimentoUseCase.execute(pedidoDTO));
        assertEquals(pedidoDTO.getId(), exception.getPedidoId());
    }

    @Test
    void givenValidPedido_whenExecute_thenCreatesAtendimento() {
        // Mocking comportamento do gateway para criar o atendimento
        LocalDateTime inicio = LocalDateTime.now();
        when(atendimentoGateway.findByPedido(pedidoDTO.getId())).thenReturn(List.of());
        when(atendimentoGateway.criar(anyLong(), anyString(), anySet()))
                .thenReturn(Atendimento.recebido(1L, pedidoDTO.getId(), pedidoDTO.getCodigo(), pedidoDTO.getProdutos()));

        // Ação
        Atendimento atendimento = createAtendimentoUseCase.execute(pedidoDTO);

        // Validação
        verify(atendimentoGateway).criar(eq(pedidoDTO.getId()), eq("Cliente X 123"), eq(pedidoDTO.getProdutos()));
        assertThat(atendimento).isNotNull();
    }

    @Test
    void givenValidPedidoWithoutCliente_whenExecute_thenUsesCodigoAsCliente() {
        // Pedido sem cliente
        pedidoDTO.setCliente(null);

        // Mocking comportamento do gateway para criar o atendimento
        LocalDateTime inicio = LocalDateTime.now();
        when(atendimentoGateway.findByPedido(pedidoDTO.getId())).thenReturn(List.of());
        when(atendimentoGateway.criar(anyLong(), anyString(), anySet()))
                .thenReturn(Atendimento.recebido(1L, pedidoDTO.getId(), pedidoDTO.getCodigo(), pedidoDTO.getProdutos()));

        // Ação
        Atendimento atendimento = createAtendimentoUseCase.execute(pedidoDTO);

        // Validação
        verify(atendimentoGateway).criar(eq(pedidoDTO.getId()), eq("123"), eq(pedidoDTO.getProdutos()));
        assertThat(atendimento).isNotNull();
    }
}
