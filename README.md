# Wallet Service

O **Wallet Service** é um microsserviço de alta performance e missão crítica responsável pela gestão de saldos e processamento de movimentações financeiras em tempo real. Desenvolvido com foco em consistência eventual, resiliência e integridade de dados.

## 🛠 Tecnologias

- **Java 21** (Virtual Threads para alta concorrência)
- **Spring Boot 3.x** (Framework core)
- **PostgreSQL** (Banco de dados relacional para consistência ACID)
- **Redis** (Cache de idempotência e travas distribuídas)
- **Kafka** (Mensageria para eventos de domínio e integração)
- **Spring Data JPA** (Persistência com Optimistic Locking)

## 🏗️ Decisões Arquiteturais

### 1. Padrão de Partida Dobrada (Double-entry Bookkeeping)

Para garantir auditoria total, o sistema não apenas altera o saldo da conta. Cada transação gera dois ou mais lançamentos imutáveis na tabela de `ledger_entries`. O saldo da conta é uma projeção agregada desses lançamentos, garantindo que nenhum centavo "desapareça".

### 2. Estratégia de Idempotência

Para evitar duplicidade em retries de rede ou falhas no cliente, o serviço implementa um controle de idempotência via cabeçalho HTTP `X-Idempotency-Key`.

- As chaves são validadas no **Redis** com um TTL de 24 horas.
- O payload da requisição é comparado para garantir que a mesma chave não esteja sendo usada para uma intenção diferente.

### 3. Gerenciamento de Concorrência

Utilizamos **Optimistic Locking** através da anotação `@Version` do JPA para evitar o problema de *Lost Update* em cenários onde múltiplos processos tentam debitar da mesma conta simultaneamente.

### 4. Observabilidade

O serviço expõe métricas via **Micrometer** para o Prometheus e utiliza **Distributed Tracing** (Sleuth/Zipkin) para rastrear transações que cruzam as fronteiras do microsserviço via Kafka.

## 🚀 Como Executar

Para rodar a aplicação em localhost, utilize o comando abaixo como perfil local
mvn spring-boot:run -Dspring-profiles-active=local -- para execução do .jar
mvn spring-boot:run -Dspring-boot.run.profiles=local
