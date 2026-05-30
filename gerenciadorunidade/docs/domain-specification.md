# Sistema de Gestão de Unidades

## Visão Geral

O sistema é responsável por administrar múltiplas unidades de negócio (filiais), permitindo:

- Gestão de funcionários
- Controle de estoque
- Gestão de produtos
- Montagem de cardápios
- Gestão de promoções
- Avaliações de funcionários
- Controle de usuários e permissões
- Auditoria de operações
- Controle de sessões de autenticação
- Geração de relatórios

---

# Enums

## UnitStatus

Representa a situação operacional de uma unidade.

| Valor     | Descrição          |
|-----------|--------------------|
| ACTIVATED | Unidade ativada    |
| INACTIVE  | Unidade desativada |

---

## EmployeeType

Representa o cargo do funcionário.

| Valor | Descrição |
|---------|------------|
| MANAGER | Gerente |
| CASHIER | Caixa |
| KITCHEN | Cozinha |

---

# Unit

Representa uma unidade física da empresa.

## Campos

| Campo | Tipo       | Descrição |
|---------|------------|------------|
| id | Long       | Identificador único |
| name | String     | Nome da unidade |
| address | String     | Endereço completo |
| status | UnitStatus | Situação da unidade |
| createdAt | Timestamp  | Data de criação |
| updatedAt | Timestamp  | Última atualização |

## Relacionamentos

Uma unidade possui:

- N Employees
- N Promotions
- N Reports
- N InventoryItems
- N Menus
- N Users

---

# Employee

Representa um colaborador vinculado a uma unidade.

## Campos

| Campo | Tipo | Descrição |
|---------|---------|------------|
| id | Long | Identificador |
| unitId | Long | Unidade vinculada |
| name | String | Nome completo |
| address | String | Endereço |
| document | String | CPF ou documento |
| birthDate | Date | Data de nascimento |
| type | EmployeeType | Cargo |
| createdAt | Timestamp | Data de criação |
| updatedAt | Timestamp | Última atualização |

## Relacionamentos

Pertence a:

- 1 Unit

Possui:

- 0..1 User
- N Evaluations

## Regras

- Documento deve ser único por funcionário.
- Não pode existir funcionário sem unidade.

---

# Evaluation

Representa avaliações realizadas sobre funcionários.

## Campos

| Campo | Tipo | Descrição |
|---------|---------|------------|
| id | Long | Identificador |
| employeeId | Long | Funcionário avaliado |
| rating | Integer | Nota |
| comment | String | Comentário |
| createdAt | Timestamp | Data de criação |
| updatedAt | Timestamp | Última atualização |

## Relacionamentos

Pertence a:

- 1 Employee

## Regras

- Rating entre 1 e 5.
- Comentário opcional.

---

# Product

Representa um produto comercializado.

## Campos

| Campo | Tipo       | Descrição |
|---------|------------|------------|
| id | Long       | Identificador |
| name | String     | Nome |
| price | BigDecimal | Preço |
| createdAt | Timestamp  | Criação |
| updatedAt | Timestamp  | Atualização |

## Relacionamentos

Possui:

- N InventoryItems
- N Menus

## Regras

- Nome obrigatório.
- Preço maior que zero.

---

# InventoryItem

Representa o estoque de um produto em uma unidade.

## Campos

| Campo | Tipo | Descrição |
|---------|---------|------------|
| id | Long | Identificador |
| unitId | Long | Unidade |
| productId | Long | Produto |
| quantity | Integer | Quantidade atual |
| minimumQuantity | Integer | Quantidade mínima |
| createdAt | Timestamp | Criação |
| updatedAt | Timestamp | Atualização |

## Relacionamentos

Pertence a:

- 1 Unit
- 1 Product

## Regras

- quantity >= 0
- minimumQuantity >= 0

---

# Menu

Representa um cardápio disponível em uma unidade.

## Campos

| Campo | Tipo | Descrição |
|---------|---------|------------|
| id | Long | Identificador |
| unitId | Long | Unidade |
| createdAt | Timestamp | Criação |
| updatedAt | Timestamp | Atualização |

## Relacionamentos

Pertence a:

- 1 Unit

Possui:

- N Products

---

# MenuProduct

Tabela associativa entre cardápios e produtos.

## Campos

| Campo | Tipo |
|---------|---------|
| menuId | Long |
| productId | Long |
| createdAt | Timestamp |
| updatedAt | Timestamp |

## Relacionamento

- N Menus ↔ N Products

---

# Promotion

Representa campanhas promocionais de uma unidade.

## Campos

| Campo | Tipo      | Descrição |
|---------|-----------|------------|
| id | Long      | Identificador |
| unitId | Long      | Unidade |
| title | String    | Nome da promoção |
| startDate | Timestamp | Início |
| endDate | Timestamp | Fim |
| reward | Enum      | Benefício concedido |
| createdAt | Timestamp | Criação |
| updatedAt | Timestamp | Atualização |

## Relacionamentos

Pertence a:

- 1 Unit

## Regras

- startDate deve ser menor que endDate.

---

# Report

Representa relatórios gerados para uma unidade.

## Campos

| Campo | Tipo      | Descrição |
|---------|-----------|------------|
| id | Long      | Identificador |
| unitId | Long      | Unidade |
| data | JSON      | Conteúdo do relatório |
| createdAt | Timestamp | Criação |
| updatedAt | Timestamp | Atualização |

## Relacionamentos

Pertence a:

- 1 Unit

## Exemplo

json {   "totalSales": 12000,   "productsSold": 250,   "period": "2026-05" }

---

# Resumo dos Relacionamentos

Unit  
├── Employees (1:N)  
├── Users (1:N)  
├── Promotions (1:N)  
├── Reports (1:N)  
├── Menus (1:N)  
└── InventoryItems (1:N)  

Employee  
├── Evaluations (1:N)  
└── User (1:0..1)  

Menu  
└── Products (N:N)  

Product  
└── InventoryItems (1:N)  

Unit  
└── InventoryItems (1:N) 