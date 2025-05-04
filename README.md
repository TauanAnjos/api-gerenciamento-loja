# Sistema Gerenciamento Loja

Este projeto é um sistema de gerenciamento de loja desenvolvido como parte de um teste técnico para uma vaga de estágio.

## Objetivo

Desenvolver uma API RESTful capaz de gerenciar produtos, usuários e pedidos de uma loja. O foco principal está na organização do código, uso de boas práticas com Spring Boot e integração com banco de dados.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- MySQL
- Docker
- Docker Compose

## Como Rodar o Projeto

Certifique-se de ter o Docker e Docker Compose instalados em sua máquina. Em seguida, execute os seguintes comandos no terminal, dentro da pasta `api-gerenciamento-loja/api-gerenciamento-loja`:

```bash
docker-compose build
docker-compose up
```
## Documentação da API
A documentação da API está disponível via Swagger, com todos os endpoints organizados e prontos para teste:

🔗 http://localhost:8080/swagger-ui/index.html#/

##Autenticação
A API utiliza autenticação baseada em JWT (JSON Web Token) com login via e-mail e senha.

Como funciona:
Envie uma requisição POST para o endpoint de login com o seguinte corpo:

```bash
{
  "email": "usuario@example.com",
  "senha": "suaSenha"
}
````
A resposta conterá um token JWT:
```bash
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
