# Sistema Bancário

Este projeto é um sistema bancário simples desenvolvido em Java que lê transações de um arquivo CSV, processa as operações de depósito e saque, e calcula o saldo de cada conta.

## Funcionalidades

- Leitura de transações a partir de um arquivo CSV
- Criação automática de contas baseada nas transações
- Cálculo de saldo para cada conta
- Exibição dos saldos no console

## Estrutura do Projeto

```
sistema-bancario/
├── operacoes_100.csv          # Arquivo de dados com transações
├── sistema-bancario.iml       # Arquivo de configuração do IntelliJ IDEA
└── src/
    └── br/com/estela/sistemabancario/
        ├── dominio/
        │   ├── Banco.java     # Classe principal que gerencia as contas
        │   ├── Conta.java     # Representa uma conta bancária
        │   └── Transacao.java # Representa uma transação (depósito ou saque)
        └── servico/
            └── LeitorCsv.java # Serviço para ler o arquivo CSV
```

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal (versão 8 ou superior, devido ao uso de `LocalDateTime`)
- **Bibliotecas Padrão do Java**:
  - `java.util.*`: Para coleções (ArrayList, SortedSet, TreeSet)
  - `java.io.*`: Para leitura de arquivos (BufferedReader, FileReader)
  - `java.time.*`: Para manipulação de datas e horas (LocalDateTime)

## Como Executar

1. Certifique-se de que o Java JDK está instalado (versão 8 ou superior).
2. Abra o projeto no IntelliJ IDEA ou compile manualmente.
3. Execute a classe `Banco` como aplicação principal.

### Compilação Manual

```bash
javac -d . src/br/com/estela/sistemabancario/dominio/*.java src/br/com/estela/sistemabancario/servico/*.java
java br.com.estela.sistemabancario.dominio.Banco
```

## Formato do Arquivo CSV

O arquivo `operacoes_100.csv` deve ter o seguinte formato:

```
AGENCIA,CONTA,BANCO,TITULAR,OPERACAO,DATAHORA,VALOR
4582,0007,ITAU,LUCIA,SAQUE,2022-02-08T16:44:55,424.15
...
```

- **AGENCIA**: Código da agência
- **CONTA**: Número da conta
- **BANCO**: Nome do banco
- **TITULAR**: Nome do titular da conta
- **OPERACAO**: Tipo de operação ("DEPOSITO" ou "SAQUE")
- **DATAHORA**: Data e hora da transação no formato ISO (yyyy-MM-ddTHH:mm:ss)
- **VALOR**: Valor da transação (ponto como separador decimal)

## Saída do Programa

O programa exibe no console o saldo de cada conta processada, no formato:

```
Banco: [BANCO] | Conta: [AGENCIA]-[CONTA] | Titular: [TITULAR] | Saldo: R$ [SALDO]
```

## Observações

- As contas são criadas dinamicamente com base nas transações encontradas no CSV.
- O saldo é calculado somando depósitos e subtraindo saques.
- As transações são ordenadas por data/hora para processamento correto.
