# Order Microservice

📌 Descrição do Projeto

Este é um microsserviço RESTful para gerenciamento de pedidos. Ele permite criar, listar e atualizar pedidos, suportando paginação, filtragem e eventos Kafka para mensageria. O sistema também inclui logging estruturado e monitoramento via DataDog.

🚀 Tecnologias Utilizadas

Java 17

Quarkus

PostgreSQL (Banco de dados relacional)

Kafka (Mensageria)

DataDog (Monitoramento e Logging)

Docker e Docker Compose

JUnit 5 e Mockito (Testes unitários e de integração)

# Endpoints da API

📌 Criar um Pedido

POST /orders

Corpo JSON:

{
"customerName": "João Silva",
"product": "Laptop",
"quantity": 2
}

📌 Listar Pedidos (com paginação e filtro)

GET /orders?customerName=Joao&status=PENDING&page=0&size=10

📌 Buscar Pedido por ID

GET /orders/{id}

📌 Atualizar Status do Pedido

PATCH /orders/{id}/status?newStatus=CONFIRMED

📌 Health Check

GET /actuator/health

# Monitoramento e Logging

A aplicação está configurada para logs estruturados e envio de métricas para o DataDog.

Acessar os logs via Docker:

docker logs -f order-microservice

Ver logs no DataDog:

Acesse DataDog Logs

Filtre pelo serviço: service:order-microservice

Ver métricas no DataDog APM:

Acesse DataDog APM

✅ Testes

Rodar testes unitários e de integração:

```shell script
mvn test