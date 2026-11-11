# API REST — Gerenciamento de Chamados

## 1. Descrição

Projeto acadêmico desenvolvido em Java e Spring Boot para gerenciar chamados de suporte técnico. A API permite cadastrar, listar, consultar e filtrar chamados por diferentes critérios.

## 2. Tecnologias utilizadas

- Java 27
- Spring Boot
- Spring Web
- Maven
- JSON
- Postman

## 3. Como executar

1. Instale o JDK compatível com o projeto.
2. Clone o repositório.
3. Abra o projeto na IDE.
4. Execute a classe `GerenciadorChamadosApplication`.
5. Acesse os endpoints pela base `http://localhost:8080`.

## 4. Endpoints

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/chamados` | Lista todos os chamados |
| GET | `/chamados/{id}` | Busca um chamado por ID |
| GET | `/chamados/setor?nome=financeiro` | Filtra por setor |
| GET | `/chamados/prioridade?nivel=alta` | Filtra por prioridade |
| GET | `/chamados/busca?texto=impressora` | Busca pelo título |
| GET | `/chamados/criticos` | Lista chamados de alta prioridade não resolvidos |
| GET | `/chamados/resumo` | Retorna quantidades por status e total |
| POST | `/chamados` | Cadastra um novo chamado |

## 5. Funcionamento do Spring Boot

O Spring Boot inicializa a aplicação web e disponibiliza um servidor HTTP. Quando uma requisição chega, o Spring identifica o endpoint correspondente e encaminha a execução ao método do Controller associado à rota.

O Controller processa a solicitação e retorna os dados ou o resultado da operação.

## 6. Função do Controller

O Controller recebe as requisições HTTP, interpreta os parâmetros, executa a lógica necessária e retorna as respostas. Neste projeto, ele também mantém a coleção de chamados em memória, conforme o escopo definido para a atividade.

## 7. Conversão entre Java e JSON

Os dados são representados como objetos Java. O Spring utiliza mecanismos de conversão HTTP, normalmente com Jackson, para transformar os objetos em JSON nas respostas. No cadastro, o JSON recebido é convertido em um objeto Java por meio de `@RequestBody`.

## 8. Armazenamento em memória

Os chamados são armazenados em uma coleção Java `ArrayList`. Como os dados não são persistidos em um banco de dados ou arquivo, os novos registros são perdidos quando a aplicação é encerrada. Ao reiniciar, os dez registros fictícios são carregados novamente.

## 9. Testes
![teste 1.png](imagensDeTeste/teste%201.png)
![teste 2.png](imagensDeTeste/teste%202.png)
![teste 3.png](imagensDeTeste/teste%203.png)
![teste 4.png](imagensDeTeste/teste%204.png)
![teste 5.png](imagensDeTeste/teste%205.png)
![teste 6.png](imagensDeTeste/teste%206.png)
![teste 7.png](imagensDeTeste/teste%207.png)
![teste 8.png](imagensDeTeste/teste%208.png)
![teste 9.png](imagensDeTeste/teste%209.png)
![teste 10.png](imagensDeTeste/teste%2010.png)

## Aluno: 
### Erlan Gleidson Barbosa Carvalho

## Disciplina: 
### MATA76 - Linguagens para Aplicação Comercial

## Professor: 
### Joselito Júnior