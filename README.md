# ⚽ ChampionShip

Projeto desenvolvido com o objetivo de aplicar conceitos de arquitetura em camadas utilizando Java puro com banco de dados H2 embarcado, explorando DAO, Service e Model para operações CRUD via menu interativo no terminal.

---

## 📌 Objective

Gerenciar partidas de futebol de forma simples através de operações CRUD (Create, Read, Update, Delete). A aplicação permite:

- Registrar partidas com times, gols e estatísticas
- Listar todas as partidas registradas
- Atualizar o placar de uma partida existente
- Deletar partidas pelo ID
- Calcular o resultado automaticamente (Vitória Time A, Vitória Time B ou Empate)

---

## ⚙️ Technologics

- Java 17+
- H2 Database (banco de dados embarcado, file-based)
- JDBC puro para acesso ao banco
- Maven para gerenciamento de dependências
- Git/GitHub para versionamento

---

## 🧠 Bussiness Logic

A lógica do sistema é baseada nos seguintes conceitos:

- **Model**: representa os dados da partida (`Partida`, `Resultado`, `Time`)
- **DAO**: camada de acesso ao banco, executa os SQLs via JDBC (`PartidaDAO`, `CampeonatoDAO`)
- **Service**: camada intermediária com validações e regras de negócio (`PartidaService`)
- **DBConnection**: singleton que gerencia a conexão com o banco H2 e inicializa as tabelas automaticamente
- **Main**: ponto de entrada da aplicação com menu interativo no terminal

O resultado da partida é calculado automaticamente ao registrar ou atualizar o placar:

| Situation | Result |
|---|---|
| Gols Time A > Gols Time B | `VITORIA_TIME_A` |
| Gols Time B > Gols Time A | `VITORIA_TIME_B` |
| Gols iguais | `EMPATE` |

---

## 📂 Project Estrutury

Campeonato/
├── src/main/java/
│   ├── Main.java                  # Menu interativo e ponto de entrada
│   ├── model/
│   │   ├── Partida.java           # Entidade principal
│   │   ├── Resultado.java         # Enum com os tipos de resultado
│   │   └── Time.java              # Modelo de time
│   ├── dao/
│   │   ├── DBConnection.java      # Conexão singleton com o H2
│   │   ├── PartidaDAO.java        # CRUD de partidas no banco
│   │   └── CampeonatoDAO.java     # CRUD de campeonatos no banco
│   └── service/
│       ├── PartidaService.java    # Regras de negócio de partidas
│       └── CampeonatoService.java # Regras de negócio de campeonatos
├── data/                          # Arquivo do banco H2 gerado em runtime
├── pom.xml
└── README.md

---

## ▶️ Execution

**Pré-requisitos:** Java 17+ e Maven instalados, ou IntelliJ IDEA.

**Pela IntelliJ (recomendado):**

1. Abra o projeto na IntelliJ IDEA
2. No painel **Maven** (lado direito) clique em **Reload All Maven Projects** 🔄
3. Abra `Main.java` e clique em **Run**

**Pelo terminal:**

```bash
git clone https://github.com/seuusuario/Campeonato.git
cd Campeonato
mvn clean package
java -jar target/Campeonato-1.0-SNAPSHOT.jar
```

---

## 💡 Use Example

====== Menu Campeonato ======
1 - Registrar partida
2 - Listar partidas
3 - Atualizar partida
4 - Deletar partida
0 - Sair
Escolha uma opção: 1
Time A: Palmeiras
Time B: Santos
Gols Time A: 2
Gols Time B: 1
Partida registrada: Partida{id=1, timeA='Palmeiras', timeB='Santos', golsTimeA=2, golsTimeB=1, resultado=VITORIA_TIME_A}

---

## 🗄️ Data Base

O banco H2 é file-based e criado automaticamente na pasta `data/` ao rodar o projeto pela primeira vez. As tabelas são inicializadas automaticamente pelo `DBConnection`.

Para visualizar os dados via console web do H2, adicione ao início do `main()`:

```java
org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
```

Acesse `http://localhost:8082` com a URL `jdbc:h2:./data/campeonato` e usuário `sa`.

---

## 👨‍🏫 Assessment

Critérios atendidos:

- Estrutura em camadas (Model, DAO, Service)
- Operações CRUD completas (registrar, listar, atualizar, deletar)
- Banco de dados H2 embarcado com inicialização automática
- Validações de negócio na camada Service
- Cálculo automático de resultado por placar
- Modularização e boas práticas de código
- Documentação clara

---
  
## Future features

- Interface front
- Spring Boot se possível
- Banco de dados em nuvem (Oracle)
- Testes Unitários
- Loombok
- Anotações
- Comentários de código para documentação 
