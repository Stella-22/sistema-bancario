package br.com.estela.sistemabancario.servico;

import br.com.estela.sistemabancario.dominio.Banco;
import br.com.estela.sistemabancario.dominio.Conta;
import br.com.estela.sistemabancario.dominio.Transacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class LeitorCsv {

    public static void lerArquivoTransacoes(Banco banco) {
        try (BufferedReader br = new BufferedReader(
                new FileReader("operacoes_100.csv"))) {

            br.readLine(); // pula o cabeçalho

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");

                String agencia  = campos[0];
                Long conta = Long.parseLong(campos[1]);
                String nomeBanco = campos[2];
                String titular = campos[3];
                String operacao = campos[4];
                LocalDateTime dataHora = LocalDateTime.parse(campos[5]);
                Double valor = Double.parseDouble(campos[6]);

                Conta contaEncontrada = banco.buscarConta(agencia, conta, nomeBanco, titular);
                if (contaEncontrada == null) {
                    contaEncontrada = new Conta(agencia, conta, nomeBanco, titular);
                    banco.adicionarConta(contaEncontrada);
                }

                Transacao transacao = new Transacao(contaEncontrada, valor, operacao, dataHora);
                contaEncontrada.adicionarTransacao(transacao);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
