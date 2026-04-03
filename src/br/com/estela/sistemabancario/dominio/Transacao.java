package br.com.estela.sistemabancario.dominio;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transacao implements Comparable<Transacao> {
    private Conta conta;
    private Double valor;
    private String operacao; // "DEPOSITO" ou "SAQUE"
    private LocalDateTime dataHora;

    public Transacao(Conta conta, Double valor, String operacao, LocalDateTime dataHora) {
        this.conta = conta;
        this.valor = valor;
        this.operacao = operacao;
        this.dataHora = dataHora;
    }

    public String getOperacao() {
        return operacao;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public int compareTo(Transacao outro) {
        if (outro == null) {
            return -1;
        }
        return this.dataHora.compareTo(outro.dataHora);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(conta, transacao.conta) &&
                Objects.equals(valor, transacao.valor) &&
                Objects.equals(operacao, transacao.operacao) &&
                Objects.equals(dataHora, transacao.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conta, valor, operacao, dataHora);
    }

}
