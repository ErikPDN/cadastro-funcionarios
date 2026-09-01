# Cadastro de Funcionários

Aplicação cliente-servidor TCP para cadastro e listagem de funcionários, com
persistência em PostgreSQL. O servidor é multi-thread (pool de 10 threads) e
atende múltiplos clientes simultaneamente.

## Pré-requisitos

- Java JDK 21 ou superior
- Maven
- PostgreSQL em execução (padrão: `localhost:5432`)

## Configuração do banco de dados

A aplicação espera um banco chamado `funcionarios`. **Ele precisa existir antes
de rodar o servidor** — a aplicação cria a tabela automaticamente, mas não o
banco.

### 1. Criar o banco

```bash
psql -U postgres -c "CREATE DATABASE funcionarios;"
```

### 2. Credenciais

As credenciais de acesso estão em
[`ConexaoDB.java`](src/main/java/trabalho/sd/rh/ConexaoDB.java):

| Parâmetro | Valor padrão                                    |
| --------- | ----------------------------------------------- |
| URL       | `jdbc:postgresql://localhost:5432/funcionarios` |
| Usuário   | `postgres`                                      |
| Senha     | `postgres`                                      |

Se o usuário `postgres` tiver outra senha, ajuste no arquivo ou defina a senha:

```bash
psql -U postgres -c "ALTER USER postgres PASSWORD 'postgres';"
```

### 3. Tabela

A tabela `funcionarios` é criada automaticamente na inicialização do servidor, a
partir de [`src/main/resources/db/schema.sql`](src/main/resources/db/schema.sql)
(`CREATE TABLE IF NOT EXISTS`, ou seja, é seguro rodar várias vezes).

Para aplicá-la manualmente, se quiser:

```bash
psql -U postgres -d funcionarios -f src/main/resources/db/schema.sql
```

## Compilando o projeto

Na raiz do projeto:

```bash
mvn compile
```

## Executando

São necessários dois terminais: um para o servidor e outro para o cliente.

### 1. Iniciar o servidor

```bash
mvn exec:java -Dexec.mainClass="trabalho.sd.rh.ServidorTcp"
```

O servidor valida a conexão com o banco, cria a tabela se necessário, sobe na
porta `5000` e fica aguardando conexões. Se o banco não estiver acessível, o
servidor encerra com erro sem abrir a porta.

### 2. Iniciar o cliente

```bash
mvn exec:java -Dexec.mainClass="trabalho.sd.rh.ClienteTcp"
```

O cliente conecta em `localhost:5000`.

### 3. Usando o cliente

Ao conectar, o cliente exibe um menu com as opções:

1. **Cadastrar funcionário** — solicita nome, cargo e salário.
2. **Listar funcionários** — exibe todos os funcionários cadastrados no servidor.
3. **Sair** — encerra a conexão.

## Estrutura do projeto

```text
src/main/java/trabalho/sd/rh/
├── ServidorTcp.java      # Servidor TCP multi-thread
├── ClienteTcp.java       # Cliente TCP interativo
├── ConexaoDB.java        # Fábrica de conexões JDBC + inicialização do schema
├── FuncionarioDAO.java   # Operações de persistência (INSERT / SELECT)
└── Funcionario.java      # Modelo de funcionário

src/main/resources/
└── db/schema.sql         # DDL da tabela funcionarios

```
