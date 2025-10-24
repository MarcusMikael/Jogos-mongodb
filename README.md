# 🎮 Catálogo de Jogos

Sistema desenvolvido em **Java** utilizando o padrão **MVC (Model-View-Controller)** e integração com o **MongoDB Atlas**, para realizar operações de **CRUD (Create, Read, Update, Delete)** em um catálogo de jogos.  
O projeto foi implementado no **NetBeans**, com interface gráfica desenvolvida em **Swing (JFrame)**.

---

## 🧩 Visão Geral

O **Catálogo de Jogos** tem como objetivo permitir o cadastro, listagem, atualização e exclusão de jogos em uma base de dados não relacional.  
Cada jogo contém os seguintes atributos:

- **ID** (gerado automaticamente pelo MongoDB)
- **Título**
- **Gênero**
- **Ano de Lançamento**
- **Preço**

O sistema oferece uma interface intuitiva que permite ao usuário interagir com o banco de dados de forma visual, sem necessidade de comandos manuais.

---

## ⚙️ Tecnologias Utilizadas

| Camada | Tecnologia |
|--------|-------------|
| Linguagem | Java |
| IDE | Apache NetBeans |
| Banco de Dados | MongoDB Atlas |
| Biblioteca de Acesso | MongoDB Java Driver |
| Arquitetura | MVC (Model, View, Controller) |
| Interface Gráfica | Java Swing (JFrame, JButton, JTable, JTextField) |

---

## 🧠 Estrutura do Projeto

📂 CatalagoDeJogos
┣ 📂 src
┃ ┣ 📂 controller
┃ ┃ ┗ 📜 JogoController.java
┃ ┣ 📂 dao
┃ ┃ ┣ 📜 JogoDAO.java
┃ ┃ ┗ 📜 MongoDBConnection.java
┃ ┣ 📂 model
┃ ┃ ┗ 📜 Jogo.java
┃ ┣ 📂 view
┃ ┃ ┗ 📜 TelaCatalogoJogos.java
┗ 📜 README.md

---

## 🏗️ Camadas

- **Model:** contém a classe `Jogo`, responsável por representar os dados do jogo e seus atributos.  
- **DAO:** camada de acesso ao banco de dados MongoDB, realizando as operações CRUD.  
- **Controller:** intermediário entre a *view* e o *DAO*, validando dados e controlando a lógica de negócio.  
- **View:** interface gráfica feita em Swing, onde o usuário interage com o sistema.

---

## 🔧 Funcionalidades

### 🟩 Adicionar Jogo
Permite o cadastro de um novo jogo no banco de dados, informando título, gênero, ano e preço.  
Caso algum campo seja inválido (por exemplo, preço negativo ou ano menor que 1900), o sistema exibe uma mensagem de erro.

### 🟦 Listar Jogos
Apresenta na tabela todos os jogos cadastrados no MongoDB, exibindo seus respectivos dados.

### 🟨 Atualizar Jogo
Após selecionar um registro na tabela, o usuário pode editar as informações e salvar as alterações.

### 🟥 Excluir Jogo
Remove permanentemente o jogo selecionado do banco de dados, com confirmação antes da exclusão.

---

## 🖥️ Interface do Sistema

A interface foi construída com **Java Swing**, priorizando clareza e simplicidade.

📂 print
┗ 🖼️ TelaCatalagoDeJogos.png

**Exemplo:**  
TelaCatalagoDeJogos exibindo o catálogo com os registros armazenados no MongoDB.

---

## 💾 Conexão com o Banco (MongoDB Atlas)

A conexão com o banco de dados é gerenciada pela classe `MongoDBConnection.java`, que utiliza a URI de conexão:

```java
String uri = "mongodb+srv://USUARIO:SENHA@SEU_CLUSTER.mongodb.net/?retryWrites=true&w=majority";
```
🚀 Como Executar o Projeto
1. Clone o repositório
bash
Copiar código
git clone https://github.com/MarcusMikael/Jogos-mongodb.git
2. Abra o projeto no NetBeans
Vá em File → Open Project

Selecione a pasta CatalagoDeJogos

3. Configure o MongoDB
Certifique-se de ter um cluster ativo no MongoDB Atlas

Atualize a URI de conexão na classe MongoDBConnection.java

4. Execute a aplicação
Rode o arquivo TelaCatalogoJogos.java

A interface gráfica será exibida e você poderá cadastrar, listar, editar e excluir jogos.

🎥 Vídeo de Apresentação
🔗 Assista ao vídeo aqui

👨‍💻 Autor
Marcus Mikael Rodrigues Vieira
