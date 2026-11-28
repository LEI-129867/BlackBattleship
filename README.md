# BlackBattleship

Testes de aceitação (caixa-preta) ao jogo [Battleship Online do papergames.io](https://papergames.io/en/battleship) — Engenharia de Software, ficha laboratorial n.º 5 (ISCTE, 2026/2027).

## Grupo
| Número | Nome |
| --- | --- |
| 129865 | Alexandra Cardoso |
| 129867 | Joana Cardoso |
| 129888 | Rúben Carvalho |

---

# Product Backlog

Product backlog em Scrum do jogo de Batalha Naval online do papergames.io. Foi construído a partir da consulta direta das páginas do site a 27/11/2026.

## Como ler este backlog

- **Formato das user stories:** *Como* &lt;papel&gt;, *quero* &lt;objetivo&gt;, *para* &lt;benefício&gt;.
- **Critérios de aceitação:** em formato **Dado / Quando / Então**. Cada critério descreve um resultado observável no browser (texto, URL, título, elemento visível), para poder ser verificado diretamente num teste de caixa-preta com Selenium.
- **Rótulos da interface:** aparecem entre aspas e em inglês, exatamente como estão no site (`/en/`), para servirem de base aos localizadores.
- **Prioridade:** MoSCoW (Must, Should, Could, Won't).
- **Estimativa:** story points (SP), escala de Fibonacci.
- **Automatizável** (com Selenium WebDriver, sem acesso ao código): ✅ sim, de forma determinística.
- **Âmbito:** só constam funcionalidades que podem ser testadas por automação de caixa-preta. Ficaram de fora o registo e início de sessão (verificação anti-robô), a penalização por abandono e o torneio diário (efeitos só visíveis em dados internos ou ao fim de 24 horas) e o pagamento de subscrições (pagamento real). Também ficaram de fora as funcionalidades cujo resultado depende de aleatoriedade, de outros jogadores ou de tempo (adversário aleatório, fim de partida, temporizador, revanche, presentes, emojis, chat e simulação de ecrã de telemóvel), por não poderem ser testadas de forma determinística.
- **¹ A confirmar na gravação:** comportamento dentro da partida que não é visível no conteúdo estático das páginas. Deve ser confirmado ao gravar o cenário no Selenium IDE.

## Resumo

| ID            | User Story | Épico | Prioridade | Estimativa | Automatizável |
|---------------| --- | --- | --- | --- | --- |
| [US01](#us01) | Abrir a página do jogo | E1 Página do jogo e navegação | Must | 1 | ✅ |
| [US02](#us02) | Navegar para o jogo a partir do menu | E1 Página do jogo e navegação | Must | 2 | ✅ |
| [US03](#us03) | Ver jogos semelhantes | E1 Página do jogo e navegação | Could | 1 | ✅ |
| [US04](#us04) | Convidar um amigo por ligação | E2 Modos de jogo e acesso | Must | 5 | ✅ |
| [US05](#us05) | Ver a classificação diária | E6 Classificações e torneios | Should | 2 | ✅ |
| [US06](#us06) | Criar um torneio privado | E6 Classificações e torneios | Should | 5 | ✅ |
| [US07](#us07) | Ler as regras do jogo | E7 Ajuda e regras | Must | 1 | ✅ |
| [US08](#us08) | Explorar a loja | E10 Loja | Could | 2 | ✅ |

---

## E1 — Página do jogo e navegação

<a id="us01"></a>
### US01 — Abrir a página do jogo

**Como** visitante, **quero** abrir a página da Batalha Naval, **para** conhecer o jogo e começar a jogar.

Prioridade: Must · Estimativa: 1 SP · Automatizável: ✅

**Critérios de aceitação**
1. **Dado** que abro `https://papergames.io/en/battleship`, **então** o título do separador é "Battleships: Battleship Online, Free Online Battleship Game".
2. **Dado** que a página carregou, **então** o cabeçalho principal mostra "Battleship Online" e o subtítulo "First to sink all opponent ships wins".
3. **Dado** que a página carregou, **então** estão visíveis os botões "Play with a friend", "Play vs robot", "Create tournament" e "Play online with a random player".

<a id="us02"></a>
### US02 — Navegar para o jogo a partir do menu

**Como** visitante, **quero** chegar à Batalha Naval pelo menu do site, **para** encontrar o jogo sem conhecer o endereço.

Prioridade: Must · Estimativa: 2 SP · Automatizável: ✅

**Critérios de aceitação**
1. **Dado** que estou em `https://papergames.io/en/`, **quando** abro o menu e escolho "Battleship" na secção "Games", **então** o URL passa a ser `https://papergames.io/en/battleship`.
2. **Dado** que estou na página da Batalha Naval, **quando** escolho "Home" no menu, **então** volto à página inicial, com o título "2 Player Games, IO Games and Paper Games - papergames.io".

<a id="us03"></a>
### US03 — Ver jogos semelhantes

**Como** jogador, **quero** ver jogos parecidos com a Batalha Naval, **para** experimentar outros jogos do site.

Prioridade: Could · Estimativa: 1 SP · Automatizável: ✅

**Critérios de aceitação**
1. **Dado** que estou na página da Batalha Naval, **então** existe a secção "Similar games" com Connect 4, Gomoku e Tic Tac Toe.
2. **Quando** clico em "Connect 4" nessa secção, **então** o URL passa a ser `https://papergames.io/en/connect4`.

---

## E2 — Modos de jogo e acesso

<a id="us04"></a>
### US04 — Convidar um amigo por ligação

**Como** jogador, **quero** criar uma partida e enviar a ligação a um amigo, **para** jogarmos os dois.

Prioridade: Must · Estimativa: 5 SP · Automatizável: ✅ (com duas sessões do WebDriver)

**Critérios de aceitação**
1. **Dado** que estou na página da Batalha Naval, **quando** clico em "Play with a friend", **então** é apresentada uma ligação única de convite, que posso copiar.¹
2. **Dado** que tenho a ligação, **quando** um segundo jogador a abre noutro browser, **então** entra na mesma partida sem ser preciso criar conta.¹
3. **Dado** que os dois jogadores estão na partida, **então** o jogo começa e é indicado de quem é a vez.¹

---

## E6 — Classificações e torneios

<a id="us05"></a>
### US05 — Ver a classificação diária

**Como** jogador, **quero** ver a classificação do dia e o tempo que falta para terminar, **para** saber a minha posição.

Prioridade: Should · Estimativa: 2 SP · Automatizável: ✅

**Critérios de aceitação**
1. **Dado** que estou na página da Batalha Naval, **então** é visível "Daily leaderboard, ends in" seguido de uma contagem decrescente no formato hh:mm:ss.
2. **Quando** clico em "See all", **então** abre a classificação completa do torneio diário.

> Nota: a 27/11/2026, o endereço da ligação "See all" era `https://papergames.io/en/t/null`. Um teste ao critério 2 pode, por isso, revelar um defeito.

<a id="us06"></a>
### US06 — Criar um torneio privado

**Como** organizador, **quero** criar um torneio privado e partilhá-lo, **para** competir só com o meu grupo.

Prioridade: Should · Estimativa: 5 SP · Automatizável: ✅

**Critérios de aceitação**
1. **Dado** que estou na página da Batalha Naval, **quando** clico em "Create tournament", **então** abre `https://papergames.io/en/t/create-tournament`.
2. **Dado** que estou nessa página, **então** existe um campo para o nome do torneio, com a indicação "This is what your friends will see".
3. **Dado** que preenchi o nome e confirmei, **então** é gerada uma ligação única para partilhar o torneio.¹

---

## E7 — Ajuda e regras

<a id="us07"></a>
### US07 — Ler as regras do jogo

**Como** visitante, **quero** ler as regras na página do jogo, **para** saber jogar antes de começar.

Prioridade: Must · Estimativa: 1 SP · Automatizável: ✅

**Critérios de aceitação**
1. **Dado** que estou na página da Batalha Naval, **então** existe a secção "Rules of Battleship game online".
2. **Dado** que estou nessa secção, **então** existem as subsecções "Gifts" e "Weapons".

---

## E10 — Loja

<a id="us24"></a>
### US24 — Explorar a loja

**Como** jogador, **quero** ver a loja, **para** saber o que posso desbloquear com moedas.

Prioridade: Could · Estimativa: 2 SP · Automatizável: ✅

**Critérios de aceitação**
1. **Quando** escolho "Shop" no menu, **então** abre `https://papergames.io/en/shop`.
2. **Dado** que estou na loja, **então** existem as categorias "Coins", "Monsters", "Emojis" e "Boosters".

---

## Fontes consultadas (27/11/2026)

- [Battleship Online](https://papergames.io/en/battleship)
- [Página inicial](https://papergames.io/en/)
- [RANKA](https://papergames.io/en/blog/ranka)
- [Create tournament](https://papergames.io/en/t/create-tournament)
- [My tournaments](https://papergames.io/en/t/my-tournaments)
- [Shop](https://papergames.io/en/shop)
- [Changelog](https://papergames.io/en/changelog)
- [Game guides](https://papergames.io/docs/game-guides/)
