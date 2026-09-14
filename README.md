# Teste Prático — Vaga Programador (SISAUDCON)

Aplicação desktop desenvolvida em Java Swing como parte do processo seletivo para a vaga de Desenvolvedor Backend Java Júnior na SISAUDCON. O sistema implementa cadastro e login de usuários (com senha criptografada em SHA-256) e cadastro de funcionários, com persistência em banco de dados PostgreSQL via JDBC nativo.

## Tecnologias utilizadas

- Java 17
- Java Swing (interface gráfica)
- PostgreSQL
- JDBC (driver `org.postgresql:postgresql`)
- Maven
- NetBeans IDE

## Funcionalidades

- **Cadastro de usuário**: nome, e-mail e senha (armazenada como hash SHA-256, nunca em texto puro)
- **Login**: autenticação por e-mail e senha, validando o hash gerado contra o hash salvo no banco
- **Cadastro de funcionários**: nome, data de admissão, salário e status (ativo/inativo)
- **Listagem de funcionários**: exibição em `JTable`, atualizada automaticamente a cada novo cadastro
- **Tratamento de erros**: mensagens amigáveis via `JOptionPane` para falhas de conexão, validação de campos e erros de banco de dados (ex: e-mail duplicado, formato de data ou salário inválido)

## Pré-requisitos

- [JDK 17](https://adoptium.net/) ou superior instalado
- [PostgreSQL](https://www.postgresql.org/download/) instalado e em execução
- [NetBeans IDE](https://netbeans.apache.org/) (opcional, mas recomendado para abrir o projeto)
- Maven (já integrado ao NetBeans)

## Configuração do banco de dados

1. Crie um banco de dados chamado `teste_saudcon` (ou o nome de sua preferência).
2. Execute o script SQL abaixo para criar as tabelas necessárias:

```sql
-- Tabela de usuários (cadastro/login)
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha_hash VARCHAR(64) NOT NULL
);

-- Tabela de funcionários
CREATE TABLE funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    data_admissao DATE NOT NULL,
    salario NUMERIC(10,2) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE
);
```

3. Configure a conexão em `src/main/java/teste/db/ConnectionFactory.java`, ajustando nome do banco, usuário e senha conforme seu ambiente local:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/teste_saudcon";
private static final String USER = "postgres";
private static final String PASSWORD = "sua_senha_aqui";
```

> **Nota de segurança:** neste projeto a senha do banco está diretamente no código-fonte por simplicidade, já que se trata de um teste técnico local. Em um ambiente de produção, o correto seria usar variáveis de ambiente ou um arquivo de configuração externo (não versionado).

## Como executar

1. Clone o repositório:
   ```
   git clone https://github.com/diaraujo115/Teste-Pratico-Java-Swing.git
   ```
2. Abra o projeto no NetBeans (`File → Open Project`).
3. Confirme que a dependência do driver PostgreSQL está resolvida (verifique `pom.xml` ou o node `Dependencies` do projeto).
4. Ajuste as credenciais do banco em `ConnectionFactory.java` (veja seção acima).
5. Execute a classe `Teste.java` (`Run File` ou `Shift+F6`) — a tela de login será aberta.
6. Clique em **"Cadastrar novo usuário"** para criar uma conta, depois faça login com essas credenciais.
7. Após o login, a tela de cadastro de funcionários é aberta, onde é possível cadastrar e visualizar os registros na tabela.

## Estrutura do projeto

```
src/main/java/teste/
├── Teste.java                    # Classe principal (ponto de entrada)
├── db/
│   └── ConnectionFactory.java    # Configuração da conexão JDBC
├── model/
│   ├── Usuario.java
│   └── Funcionario.java
├── dao/
│   ├── UsuarioDAO.java
│   └── FuncionarioDAO.java
├── util/
│   └── SenhaUtil.java            # Geração de hash SHA-256
└── view/
    ├── TelaLogin.java
    ├── TelaCadastroUsuario.java
    └── TelaCadastroFuncionario.java
```

## Autor

Diego — [GitHub](https://github.com/diaraujo115)
