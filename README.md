# ⚽ Campeonato

Projeto desenvolvido com o objetivo de aplicar conceitos de arquitetura em camadas utilizando Java puro com banco de dados H2 embarcado, explorando DAO, Service e Model para operações CRUD via menu interativo no terminal.

---

## 📌 Objetivo

Gerenciar partidas de futebol de forma simples através de operações CRUD (Create, Read, Update, Delete). A aplicação permite:

- Registrar partidas com times, gols e estatísticas
- Listar todas as partidas registradas
- Atualizar o placar de uma partida existente
- Deletar partidas pelo ID
- Calcular o resultado automaticamente (Vitória Time A, Vitória Time B ou Empate)

---

## ⚙️ Tecnologias

- Java 17+
- H2 Database (banco de dados embarcado, file-based)
- JDBC puro para acesso ao banco
- Maven para gerenciamento de dependências
- Git/GitHub para versionamento

---

## 🧠 Lógica de Negócio

A lógica do sistema é baseada nos seguintes conceitos:

- **Model**: representa os dados da partida (`Partida`, `Resultado`, `Time`)
- **DAO**: camada de acesso ao banco, executa os SQLs via JDBC (`PartidaDAO`, `CampeonatoDAO`)
- **Service**: camada intermediária com validações e regras de negócio (`PartidaService`)
- **DBConnection**: singleton que gerencia a conexão com o banco H2 e inicializa as tabelas automaticamente
- **Main**: ponto de entrada da aplicação com menu interativo no terminal

O resultado da partida é calculado automaticamente ao registrar ou atualizar o placar:

| Situação | Resultado |
|---|---|
| Gols Time A > Gols Time B | `VITORIA_TIME_A` |
| Gols Time B > Gols Time A | `VITORIA_TIME_B` |
| Gols iguais | `EMPATE` |

---

## 📂 Estrutura do Projeto
