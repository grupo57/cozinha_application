package br.com.fiap.soat07.techchallenge.cozinha.infra.repository.mysql.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration.SituacaoDoAtendimento;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ATENDIMENTO")
public class AtendimentoModel {
	
	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private Long idPedido;

	@Column
	private String codigo;

	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	private SituacaoDoAtendimento situacao;

	@Column
	private LocalDateTime dataRecebido;

	@Column
	private LocalDateTime dataIniciado;

	@Column
	private LocalDateTime dataConcluido;

	@CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime dataUltimaModificacao;



	protected AtendimentoModel() {
		this.situacao = SituacaoDoAtendimento.RECEBIDO;
		this.dataCriacao = LocalDateTime.now();
		this.dataRecebido = dataCriacao;
	}
	public AtendimentoModel(Long idPedido, String codigo) {
		this();
		this.idPedido = idPedido;
		this.codigo = codigo;
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
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public SituacaoDoAtendimento getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoDoAtendimento situacao) {
		this.situacao = situacao;
		this.dataUltimaModificacao = LocalDateTime.now();
	}

	public LocalDateTime getDataRecebido() {
		return this.dataRecebido;
	}
	public LocalDate getData() {
		return getDataRecebido().toLocalDate();
	}
	public void setDataRecebido(LocalDateTime dataRecebido) {
		this.dataRecebido = dataRecebido;
	}

	public LocalDateTime getDataIniciado() {
		return dataIniciado;
	}
	public void setDataIniciado(LocalDateTime dataIniciado) {
		this.dataIniciado = dataIniciado;
	}

	public LocalDateTime getDataConcluido() {
		return dataConcluido;
	}
	public void setDataConcluido(LocalDateTime dataConcluido) {
		this.dataConcluido = dataConcluido;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	@Override
	public String toString() {
		return "PedidoModel{" +
				"id=" + id +
				", pedido='" + idPedido + '\'' +
				", data='" + getData() + '\'' +
				", codigo='" + codigo + '\'' +
				", status=" + situacao +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AtendimentoModel that)) return false;
        return getData() == that.getData() && Objects.equals(getCodigo(), that.getCodigo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getData(), getCodigo());
	}
}