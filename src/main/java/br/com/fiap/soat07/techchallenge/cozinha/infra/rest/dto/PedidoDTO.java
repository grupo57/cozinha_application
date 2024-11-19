package br.com.fiap.soat07.techchallenge.cozinha.infra.rest.dto;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.PedidoStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoDTO {
    private Long id;
    private String cliente;
    private String codigo;
    private Set<ProdutoDTO> produtos;
    private PedidoStatusEnum status;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<ProdutoDTO> getProdutos() {
        if (produtos == null)
            return Collections.emptySet();
        return produtos;
    }
    public void setProdutos(Set<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getValor() {
        if (getProdutos() == null || getProdutos().isEmpty()) {
            return BigDecimal.ZERO;
        }

        return getProdutos().stream()
                .map(ProdutoDTO::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
