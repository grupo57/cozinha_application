package br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.TipoProdutoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Produto {

	private Long id;
	private String codigo;
	private String nome;
	private BigDecimal valor;
	private TipoProdutoEnum tipoProduto;

	public Long getId() {
		return this.id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public BigDecimal getValor() {
		if (valor == null)
			return BigDecimal.ZERO;
		return this.valor;
	}

	public TipoProdutoEnum getTipoProduto() {
		return this.tipoProduto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setValor(BigDecimal valor) {
		if (valor == null)
			valor = BigDecimal.ZERO;
		this.valor = valor;
	}

	public void setTipoProduto(TipoProdutoEnum tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String toString() {
		return "Produto(id=" + this.getId() + ", codigo=" + this.getCodigo() + ", nome=" + this.getNome() + ", valor=" + this.getValor() + ", tipoProduto=" + this.getTipoProduto() + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Produto produto)) return false;
        return Objects.equals(getCodigo(), produto.getCodigo()) && Objects.equals(getNome(), produto.getNome()) && Objects.equals(getValor(), produto.getValor()) && getTipoProduto() == produto.getTipoProduto();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCodigo(), getNome(), getValor(), getTipoProduto());
	}

}