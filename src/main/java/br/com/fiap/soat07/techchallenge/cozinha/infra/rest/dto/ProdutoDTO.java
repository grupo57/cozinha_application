package br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.TipoProdutoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String codigo;
    private BigDecimal valor;
    private TipoProdutoEnum tipoProduto;

    public ProdutoDTO(Long id, String nome, String codigo, BigDecimal valor, TipoProdutoEnum tipoProduto) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.tipoProduto = tipoProduto;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValor() {
        if (valor == null)
            return BigDecimal.ZERO;
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoProdutoEnum getTipo() {
        return tipoProduto;
    }
    public void setTipo(TipoProdutoEnum tipo) {
        this.tipoProduto = tipo;
    }
}
