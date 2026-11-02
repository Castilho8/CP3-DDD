# Inventario — Checkpoint 3 (Domain Driven Design – Java)

Sistema de inventário em **Java 17 + Spring Boot** para cadastrar produtos de qualquer categoria (CDs, DVDs, softwares, material de escritório etc.) e **gravar as informações no banco de dados** com Spring Data JPA.

## Integrante

| Nome | RM |
|---|---|
| Bruno Santos Castilho | RM566799 |

Disciplina: Domain Driven Design – Java · Professor: Carlos Eduardo Machado de Oliveira · Trabalho individual

---

## Tarefa 2 — Produtos do inventário

| # | Nome do produto | Preço | Unidades em estoque | Número do item |
|---|---|---|---|---|
| 1 | CD Greatest Hits | R$ 29.99 | 25 | 1 |
| 2 | DVD Interestelar | R$ 39.90 | 12 | 2 |
| 3 | Software Microsoft Office 365 - Licença 1 ano | R$ 349.00 | 8 | 3 |
| 4 | Caneta Esferográfica Azul - Caixa c/ 50 | R$ 45.50 | 30 | 4 |
| 5 | Caderno Universitário 10 Matérias | R$ 32.90 | 40 | 5 |
| 6 | Software Antivírus - Licença 1 ano | R$ 129.90 | 15 | 6 |

Os produtos 1 e 2 são digitados pelo usuário ao executar o programa (p1 e p2). Os produtos 3 a 6 já estão no código (p3 a p6).

## Tarefa 3 — Atributos e tipos de dados

| Atributo | Dados de amostra | Tipo de dados |
|---|---|---|
| Nome do produto | CD Greatest Hits | `String` |
| Preço | 29.99 | `double` |
| Número de unidades em estoque | 25 | `int` |
| Número do item | 1 | `int` |
| Ativo (tarefa 15) | true | `boolean` |

---

## Estrutura do projeto

```
Inventario/
├── pom.xml
└── src/
    ├── main/java/br/com/fiap/inventario/
    │   ├── ProductTester.java              → classe driver + classe principal do Spring Boot
    │   ├── model/Produto.java              → classe Produto (entidade JPA)
    │   ├── repository/ProdutoRepository.java → acesso ao banco (Spring Data JPA)
    │   └── service/ProdutoService.java     → grava/lista os produtos no banco
    ├── main/resources/application.properties → configuração do banco H2
    └── test/java/...                       → testes da classe Produto e do repositório
```

## Onde cada tarefa foi atendida

| Tarefa | Onde |
|---|---|
| 1 e 4 | Projeto Spring Boot `Inventario` (`pom.xml`) |
| 5 a 9 | `Produto.java`: campos privados, construtor padrão e com parâmetros, getters/setters, `toString()` |
| 10 a 12 | `ProductTester.java`: seis objetos (p3 e p4 com construtor padrão, os demais com parâmetros) exibidos no console |
| 13 e 14 | `Scanner`, variáveis `tempNumber`, `tempName`, `tempQty`, `tempPrice`, leitura de p1 e p2, limpeza do buffer (`nextLine()`) e `close()` |
| 15 e 16 | Campo `ativo` (padrão `true`) e `getStatusProduto()` que exibe "Ativo"/"Descontinuado" |
| 17 | `p1.setAtivo(false)` antes de exibir |
| 18 e 19 | `getValorEstoque()` (preço × quantidade em uma linha) incluído no `toString()` |
| 20 | `maxSize` lido com `do while`, exibindo "Valor incorreto inserido" para valores negativos |
| 21 a 23 | `if` para "Não há produtos!", array de `Produto` com `maxSize` posições, `for` para preencher e `for each` para exibir |
| 24 | Spring Data JPA + banco **H2** em arquivo (`./data/inventario`): todos os produtos são gravados na tabela `TB_PRODUTO` e listados no final |

## Banco de dados

Foi usado o **H2** em modo arquivo, que não precisa de instalação: a tabela `TB_PRODUTO` é criada automaticamente pelo Hibernate e os dados ficam salvos na pasta `data/` entre as execuções.

## Como executar

Requisitos: **JDK 17 ou superior** e Maven (o IntelliJ IDEA já vem com Maven embutido).

**IntelliJ IDEA:** abra a pasta do projeto, aguarde o Maven baixar as dependências e execute a classe `ProductTester` (botão ▶ ao lado do `main`).

**Terminal:**

```bash
mvn spring-boot:run
```

Para rodar os testes: `mvn test`.

> O preço pode ser digitado com ponto ou vírgula (`29.99` ou `29,99`). Se algum valor numérico for digitado errado, o programa mostra "Valor incorreto inserido" e pede de novo.

### Exemplo de saída

```
Número do Item : 1
Nome : CD Greatest Hits
Quantidade em Estoque : 25
Preço : R$ 29.99
Valor do Estoque : R$ 749.75
Status do Produto : Descontinuado
```
