# Client API com Spring Boot e REST
Esse projeto é uma demonstração de implementação das técnicas de desenvolvimento Back-end.
O objetivo foi desenvolver no modelo de camadas da restrição REST as funcionalidades básicas de um sistema Web com as operações de CRUD para o consumo de recursos.
A entidade foi construída baseada no seguinte diagrama:

![image](https://github.com/user-attachments/assets/735ec6ef-50a2-499b-907f-e5b0b4d06da0)

## Padrões e Técnicas utilizadas
- **Maven** – Utilizado para o build do projeto.  
- **Layered Architecture** – Código organizado em camadas com responsabilidades bem definidas.  
- **DTO** – Objetos de transferência de dados: um para entrada (com validações) e outro para resposta.  
- **Spring Data JPA** – O repositório da entidade é uma interface que estende `JpaRepository`.  
- **ExceptionHandler** – Exceções tratadas com respostas adequadas e mensagens explicativas.  
- **Page** – As buscas de recursos são paginadas.  
- **H2 Database** – Banco em memória para testes com dados inseridos via `import.sql`.  
- **Java 21** – Projeto desenvolvido com a versão 21 da linguagem.
## Como executar o projeto
1. Clone o repositório para uma máquina com JVM:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git

2. Importe o projeto na sua IDE de preferência (Eclipse, IntelliJ, etc.) e execute.

3. Utilize o Postman ou outra ferramenta de requisição HTTP para realizar testes nos seguintes endpoints:

### Exemplos de uso:

### GET `/clients`

**Busca paginada com ordenação decrescente por renda:**

```http
GET /clients?size=3&page=2&sort=income,desc
```
### POST `/clients`

**Exemplo de requisição:**

```json
{
"name": "Maria Silva",
"cpf": "12345678901",
"income": 6500.0,
"birthDate": "1994-07-20",
"children": 2
}
```
### PUT `/clients/2`

```json
{
 "name": "Beatriz",
    "cpf": "143.589.410-36",
    "income": 4000.0,
    "birthDate": "1999-01-20",
    "children": 2
}
```
### DELETE `/clients/3`
```http
DELETE http://localhost:8080/clients/2
