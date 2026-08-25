## Pré-requisitos

- Java JDK 21 ou superior
- Maven

## Compilando o projeto

Na raiz do projeto, execute:

```bash
mvn compile
```

## Executando

São necessários dois terminais: um para o servidor e outro para o cliente.

### 1. Iniciar o servidor

```bash
mvn exec:java -Dexec.mainClass="trabalho.sd.rh.ServidorTcp"
```

O servidor sobe na porta `5000` e fica aguardando conexões.

### 2. Iniciar o cliente

```bash
 mvn exec:java -Dexec.mainClass="trabalho.sd.rh.ClienteTcp"
```

### 3. Usando o cliente

Ao conectar, o cliente exibe um menu com as opções:

1. **Cadastrar funcionário** — solicita nome, cargo e salário.
2. **Listar funcionários** — exibe todos os funcionários cadastrados no servidor.
3. **Sair** — encerra a conexão.

## Estrutura do projeto

```
src/main/java/trabalho/sd/rh/
├── ServidorTcp.java   # Servidor TCP multi-thread
├── ClienteTcp.java    # Cliente TCP
└── Funcionario.java   # Modelo de funcionário
```
