# BooksAPI-Client
📚 BookManager – Aplicação CRUD de Livros com Java Swing e Spring Boot
BookManager é uma aplicação para gerenciar um catálogo de livros, desenvolvida com Java Swing no frontend e Spring Boot no backend. O sistema permite cadastrar, listar, atualizar e excluir livros através de uma API REST, utilizando OkHttpClient para as requisições HTTP.

🚀 Tecnologias Utilizadas
🔹 Frontend: Java Swing (Interface gráfica)
🔹 Backend: Spring Boot (API REST)
🔹 Banco de Dados: SQL Server
🔹 Comunicação: OkHttpClient (Consumo da API)
🔹 Segurança: JWT para autenticação

🔧 Funcionalidades
✅ Cadastro de livros
✅ Listagem de livros
✅ Atualização de informações dos livros
✅ Remoção de livros
✅ Autenticação com Token JWT

📂 Estrutura do Projeto
📁 backend → API REST desenvolvida com Spring Boot
📁 frontend → Interface Java Swing que consome a API

⚡ Como Executar
1️⃣ Clonar o Repositório
bash
Copiar
Editar
git clone https://github.com/seu-usuario/BookManager.git
cd BookManager
2️⃣ Configurar o Banco de Dados
Criar um banco no SQL Server

Configurar as credenciais no application.properties do backend

3️⃣ Executar o Backend
bash
Copiar
Editar
cd backend
mvn spring-boot:run
4️⃣ Executar o Frontend
Rodar o projeto Java Swing pelo seu IDE (NetBeans, Eclipse, VS Code, etc.)

🛠 Melhorias Futuras
🔹 Filtro de livros por autor/gênero
🔹 Interface aprimorada no Swing
🔹 Exportação de dados para CSV/PDF

