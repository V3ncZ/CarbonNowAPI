# 🌱 CarbonNowAPI

**CarbonNowAPI** é uma API desenvolvida com **Spring Boot** que tem como objetivo, no futuro, realizar cálculos de pegada de carbono. Atualmente, o projeto está na fase de estruturação, contendo os principais endpoints, autenticação com JWT e toda a base pronta para evoluir.

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Security (JWT)
- Spring Data JPA
- H2 Database (para testes locais)
- Swagger/OpenAPI (documentação)
- Maven
- Cucumber
- Rest Assured

## 🏗️ Status do projeto

✅ Estruturação inicial  
✅ Autenticação com JWT  
✅ CRUD básico de entidades  
✅ Testes Unitarios aplicando BDD
✅ Documentação via Swagger  
🔜 Futuro: Implementação dos cálculos de emissão de CO₂

## 📑 Funcionalidades atuais

- 🔐 Sistema de autenticação (login e geração de token JWT)
- 🧾 Endpoints organizados por contexto
- 🔐 Segurança aplicada nas rotas
- 🗂️ Documentação da API disponível no Swagger
- 🗂️ Testes com BDD para validações de comportamento

## 🚀 Como executar o projeto localmente

### 1. Clone o repositório
```bash
git clone https://github.com/V3ncZ/CarbonNowAPI.git
cd CarbonNowAPI
```
### 2. Execute o projeto
Se estiver em uma IDE, execute a classe CarbonNowApiApplication.
Ou via terminal:

```bash
./mvnw spring-boot:run
```

### 3. Acesse a aplicação

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html

## 🐳 Como executar o projeto DockerFile

### 1. Execute o seguinte comando para criar a imagem do projeto
```bash
docker build -t carbon-now-api .
```

### 2. Agora execute o container a partir da imagem criada
```bash
docker run -d --name carbonnow -p 8080:8080 carbon-now-api
```

## 🔐 Autenticação
- Acesse o endpoint /auth/login e envie suas credenciais.
- O retorno será um token JWT.
- No Swagger UI, clique em Authorize e insira:

```bash
Bearer {seu_token}
```
Agora você pode acessar os endpoints protegidos.

## 📂 Estrutura do projeto

```bash
src
├── main
│   └── java
│       └── br.com.carbonNow.carbonNowAPI
│           ├── advice
│           ├── config.security
│           ├── controller
│           ├── domain
│           ├── dto
│           ├── exception
│           ├── repository
│           ├── service
│           └── CarbonNowApiApplication.java
└── test
    ├── java
    │   └── br.com.carbonNow.carbonNowAPI
    │       ├── config
    │       ├── hook
    │       ├── runner
    │       ├── service
    │       └── steps
    └── resources
        ├── features
        └── schemas
```

## 📜 Documentação dos endpoints
Acesse no navegador:

```bash
http://localhost:8080/swagger-ui/index.html
```

## 🛠️ Funcionalidades futuras

- Implementação dos cálculos de pegada de carbono (transporte, eletricidade, etc.)
- Validações de dados mais robustas
- Deploy na nuvem

## 🤝 Contribuição
Sinta-se à vontade para contribuir! Sugestões, issues e pull requests são bem-vindos.
