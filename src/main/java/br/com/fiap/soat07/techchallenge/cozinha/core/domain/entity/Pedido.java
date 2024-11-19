package br.com.fiap.soat07.techchallenge.cozinha.core.domain.entity;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.PedidoStatusEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class Pedido {
	
	private Long id;
	private String nomeCliente;
	private String codigo;
	private Set<Produto> produtos;
	private PedidoStatusEnum status;

	public Pedido() {
		this.produtos = new HashSet<>();
	}
	public Pedido(Long id, String nomeCliente, String codigo, PedidoStatusEnum status) {
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.codigo = codigo;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nomeCliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public PedidoStatusEnum getStatus() {
		return status;
	}

	public Set<Produto> getProdutos() {
		if (produtos == null)
			this.produtos = new HashSet<>();
		return produtos;
	}

	public BigDecimal getValor() {
		if (getProdutos() == null)
			return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);

		return getProdutos().stream()
				.map(Produto::getValor)
		        .reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public String toString() {
		return nomeCliente == null || nomeCliente.isEmpty() ? codigo : nomeCliente;
	}

}