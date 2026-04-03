package br.com.estela.sistemabancario.dominio;

import br.com.estela.sistemabancario.servico.LeitorCsv;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Conta> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    public Conta buscarConta(String agencia, Long numero, String banco, String titular) {
        return contas.stream()
                .filter(c -> c.getAgencia().equals(agencia) &&
                             c.getNumero().equals(numero) &&
                             c.getBanco().equals(banco) &&
                             c.getTitular().equals(titular))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        Banco banco = new Banco();
        LeitorCsv.lerArquivoTransacoes(banco);

        // Exibe o saldo de cada conta após processar as transações
        banco.contas.forEach(conta -> {
            Double saldo = conta.calcularSaldo();
            System.out.printf(
                    "Banco: " + conta.getBanco() +
                    " | Conta: " + conta.getAgencia() + "-" + conta.getNumero() +
                    " | Titular: " + conta.getTitular() +
                    " | Saldo: R$ %.2f" +
                    "\n", saldo);
        });
    }
}
