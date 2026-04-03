package br.com.estela.sistemabancario.dominio;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Conta {
    private String agencia;
    private Long numero;
    private String banco;
    private String titular;
    private Double saldo;
    private SortedSet<Transacao> operacoes;   // Organiza todas essas operações por titular, sem repetições

    public Conta(String agencia, Long numero, String banco, String titular) {
        this.agencia = agencia;
        this.numero = numero;
        this.banco = banco;
        this.titular = titular;
        this.saldo = 0.0;
        this.operacoes = new TreeSet<>();
    }

    public String getAgencia() {
        return agencia;
    }

    public Long getNumero() {
        return numero;
    }

    public String getBanco() {
        return banco;
    }

    public String getTitular() {
        return titular;
    }

    public void adicionarTransacao(Transacao transacao) {
        this.operacoes.add(transacao);
    }

    public Double calcularSaldo() {
        // Percorre as transações e calcula o saldo com base nas operações de depósito e saque
        operacoes.stream()
                .filter(t -> "DEPOSITO".equals(t.getOperacao()) && t.getValor() > 0.0)
                .forEach(t -> saldo += t.getValor());

        operacoes.stream()
                .filter(t -> "SAQUE".equals(t.getOperacao()) && t.getValor() > 0.0)
                .forEach(t -> saldo -= t.getValor());

        return this.saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(agencia, conta.agencia) &&
                Objects.equals(numero, conta.numero) &&
                Objects.equals(banco, conta.banco) &&
                Objects.equals(titular, conta.titular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, numero, banco, titular);
    }
}
