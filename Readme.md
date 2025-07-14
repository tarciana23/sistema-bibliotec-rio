# 📚 API de Gerenciamento de Biblioteca

API RESTful desenvolvida para gerenciar os recursos de uma biblioteca. O sistema permite, inicialmente, realizar operações de CRUD com livros, e será expandido para incluir autores, usuários e controle de empréstimos.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Lombok
- Banco de dados PostgreSQL
- Swagger

---

## 📘 Funcionalidade atuais

### 🔧 Operações disponíveis

- `POST /autor/cadastrarAutor` → Cadastrar novo autor
- `GET /autor/listarTodos` → Listar todos os autores
- `GET /autor/buscarUmAutor/{id}` → Buscar autor por ID
- `PUT /autor/{id}` → Atualizar informações de um autor
- `DELETE /autor/{id}` → Remover autor do sistema
---

- `POST /livro` → Cadastrar novo livro
- `GET /livro` → Listar todos os livros
- `GET /livro/{id}` → Buscar livro por ID
- `PUT /livro/{id}` → Atualizar informações de um livro
- `DELETE /livro/{id}` → Remover livro do sistema
---
- `POST /usuario` → Cadastrar novo usuário
- `GET /usuario` → Listar todos os usuários
- `GET /usuario/buscarUm/1` → Buscar usuário por ID
- `PUT /usuario/{id}` → Atualizar informações de um usuário
- `DELETE /usuario/1` → Remover usuário do sistema
---
- `POST /emprestimos` → Cadastrar novo empréstimo
- `GET /emprestimos` → Listar todos os empréstimos
- `GET /emprestimos/livro/{id}` → Buscar empréstimo por id do livro
- `GET /emprestimos/usuario/{id}` → Buscar empréstimo por id do usuário
- `GET /emprestimos/usuario/{id}/tem-emprestimo` → Verificar se um usuário tem empréstimo
- `PUT /emprestimos/devolver/{id}` → Devolver um livro e encerrar o empréstimo

### 📄 Exemplo de JSON para cadastro:

```json
{
  "idLivro": 1,
  "titulo": "Grande Sertão de Veredas",
  "isbn": "987456321",
  "anoPublicacao": 1956,
  "autorId": 2,
  "disponivel": true
}
````

```json
{
  "nome" : "Guimarães rosa",
  "nacionalidade" : "brasileiro"
}
````

```json
{
  "nome": "Tarciana",
  "email": "tarcianasoliveira23@gmail.com",
  "telefone" : "77988444393"
}
````

```json
{
  "livroId" : 3,
  "usuarioId" : 3,
  "dataEmprestimo" : "2025-07-14T13:45:00"
}
````

---

## 🛠️ Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/tarciana23/biblioteca-api.git
```

2. Navegue até a pasta do projeto:

```bash
cd biblioteca-api
```

3. Compile e rode o projeto:

```bash
./mvnw spring-boot:run
```

4. Acesse o sistema:

* API: `http://localhost:8080/autor/cadastrarAutor`
* Swagger: [em construção 🚧]

---

## 📈 Próximas Etapas

* [x] CRUD de Livros
* [x] CRUD de Autores
* [x] CRUD de Usuários
* [x] Sistema de Empréstimos e Devoluções
* [ ] Documentação com Swagger
* [ ] Testes unitários
* [ ] Autenticação e segurança da API
* [ ] Deploy

---

## 👩‍💻 Desenvolvido por

**Tarciana Oliveira** . 
Desenvolvedora Java em formação — apaixonada por backend, APIs e aprender todos os dias.

[LinkedIn](https://www.linkedin.com/in/tarciana-souza-oliveira-72127021a/) · [Portfólio](https://portifolio-tarciana.vercel.app/)

---

> “Todo grande projeto começa com um CRUD bem feito.” 🚀
