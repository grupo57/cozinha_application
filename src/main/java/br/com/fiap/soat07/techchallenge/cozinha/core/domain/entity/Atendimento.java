package br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto.ProdutoDTO;

import java.time.LocalDateTime;
import java.util.Collection;

public class Atendimento {
    private Long id;
    private Long idPedido;
    private String codigo;
    private SituacaoDoAtendimento situacao;
    private LocalDateTime inicio;
    private LocalDateTime preparo;
    private LocalDateTime concluido;

    //
    protected Atendimento() {
        this.inicio = LocalDateTime.now();
    }
    public Atendimento(Long id, Long idPedido, String codigo, SituacaoDoAtendimento situacao, LocalDateTime inicio, LocalDateTime preparo, LocalDateTime concluido, Collection<ProdutoDTO> produtos) {
        this();
        this.id = id;
        this.idPedido = idPedido;
        this.codigo = codigo;
        this.situacao = situacao;
        this.inicio = inicio;
        this.preparo = preparo;
        this.concluido = concluido;
    }

    public static Atendimento recebido(Long id, Long idPedido, String codigo, Collection<ProdutoDTO> produtos) {
        return new Atendimento(id, idPedido, codigo, SituacaoDoAtendimento.RECEBIDO, LocalDateTime.now(), null, null, produtos);
    }

    public Long getId() {
        return id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public String getCodigo() {
        return codigo;
    }

    public SituacaoDoAtendimento getSituacao() {
        return situacao;
    }
    public void setSituacao(SituacaoDoAtendimento situacao) {
        this.situacao = situacao;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getPreparado() {
        return preparo;
    }
    public void setPreparo(LocalDateTime preparo) {
        this.preparo = preparo;
    }

    public LocalDateTime getConcluido() {
        return concluido;
    }
    public void setConcluido(LocalDateTime concluido) {
        this.concluido = concluido;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "id=" + id +
                ", pedido='" + getIdPedido() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", situacao='" + getSituacao() + '\'' +
                '}';
    }
}
