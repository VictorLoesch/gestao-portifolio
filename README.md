# Gestão de Portfólio

Este é o repositório do **Gestão de Portifólio**, uma aplicação Spring Boot em Java para gerenciar o portfólio de projetos de uma empresa.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.5.4 (MVC, Data JPA)
- Hibernate
- JSP + JSTL (ISO-8859-1)
- Bootstrap 5
- Maven (packaging WAR)
- PostgreSQL
- Docker & Docker Compose
- Testes: JUnit 5, Mockito, Spring MVC Test

## 📁 Estrutura de Pastas

```
gestao_portifolio/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java/com/desafio/gestao_portifolio
│   │   ├── resources
│   │   ├── static
│   │   └── webapp/WEB-INF/jsp
│   └── test
└── target
```

## 🔍 Funcionalidades

### Página Inicial

- **Rota:** `GET http://localhost:8080/`
- Exibe uma tela simples (`index.jsp`) com links para Pessoas e Projetos.

### Pessoas

- **Listar pessoas:** `GET http://localhost:8080/pessoas`
- **Formulário (novo/editar):** `GET http://localhost:8080/pessoas/novo` ou `/pessoas/editar/{id}`
- **Salvar:** `POST http://localhost:8080/pessoas` (formulário HTML)
- **Excluir:** `POST http://localhost:8080/pessoas/excluir/{id}`

### Projetos

- **Listar projetos:** `GET http://localhost:8080/projetos`
- **Formulário (novo/editar):** `GET http://localhost:8080/projetos/novo` ou `/projetos/editar/{id}`
- **Salvar:** `POST http://localhost:8080/projetos`

  - Campos:

    - `nome` (String)
    - `gerente.id` (Long)
    - `status` (um dos valores de StatusProjeto)
    - `nivelRisco` (um dos valores de NivelRisco)
    - `membrosId` (lista de IDs de pessoas)

- **Excluir:** `POST http://localhost:8080/projetos/excluir/{id}`

#### Valores possíveis

- **StatusProjeto:** `EM_ANALISE`, `PLANEJADO`, `INICIADO`, `EM_ANDAMENTO`, `ENCERRADO`, `CANCELADO`
- **NivelRisco:** `BAIXO`, `MEDIO`, `ALTO`

## 🔌 API REST

Existe também um endpoint REST para cadastro de pessoas:

```bash
curl -X POST http://localhost:8080/api/pessoas \
  -H 'Content-Type: application/json' \
  -d '{
    "nome": "João Silva",
    "dataNascimento": "1990-05-12",
    "cpf": "123.456.789-00",
    "funcionario": true,
    "gerente": false
}'
```

Resposta exemplo (JSON):

```json
{
  "id": 1,
  "nome": "João Silva",
  "dataNascimento": "1990-05-12",
  "cpf": "123.456.789-00",
  "funcionario": true,
  "gerente": false
}
```

## 🐳 Docker

Para rodar a aplicação e o banco com Docker Compose:

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/gestao_portifolio.git
cd gestao_portifolio

# 2. Suba os containers (app + PostgreSQL)
docker-compose up --build -d

# 3. Acesse no navegador:
#    • http://localhost:8080/
#    • http://localhost:8080/pessoas
#    • http://localhost:8080/projetos

# 4. Para parar e remover:
docker-compose down -v
```

## 🧪 Testes

- **Unitários:** `mvn test` (JUnit 5 + Mockito)
- **Controllers:** cobertos com Spring MVC Test (MockMvc)

---

**Desenvolvido por você, pronto para gerenciar seu portfólio de projetos!**
