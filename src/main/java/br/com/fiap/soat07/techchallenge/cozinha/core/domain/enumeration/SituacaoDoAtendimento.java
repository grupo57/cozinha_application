package br.com.fiap.soat07.techchallenge.cozinha.core.domain.enumeration;

public enum SituacaoDoAtendimento {
    RECEBIDO,
    INICIADO,
    PREPARADO,
    ENTREGUE,
    CANCELADO;

    /**
     * O atendimento é considerado encerrado quando não há mais o que fazer.
     * @return boolean
     */
    public boolean isEncerrado() {
        return this.equals(SituacaoDoAtendimento.ENTREGUE) || this.equals(SituacaoDoAtendimento.CANCELADO);
    }

}
