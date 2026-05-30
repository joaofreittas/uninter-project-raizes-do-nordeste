Abaixo está uma especificação funcional e estrutural baseada no seu diagrama de classes e no DER. O objetivo é servir como documentação para um agente de IA implementar as entidades, relacionamentos, validações e regras básicas de negócio.

⸻

Sistema de Gestão de Unidades

Visão Geral

O sistema é responsável por administrar múltiplas unidades de negócio (filiais), permitindo:

* Gestão de funcionários
* Controle de estoque
* Gestão de produtos
* Montagem de cardápios
* Gestão de promoções
* Avaliações de funcionários
* Controle de usuários e permissões
* Auditoria de operações
* Controle de sessões de autenticação
* Geração de relatórios

⸻

Enums

UnitStatus

Representa a situação operacional de uma unidade.

Valor	Descrição
ACTIVATED	Unidade ativada
INACTIVE	Unidade desativada

⸻

EmployeeType

Representa o cargo do funcionário.

Valor	Descrição
MANAGER	Gerente
CASHIER	Caixa
KITCHEN	Cozinha

⸻

Entidades

⸻

Unit

Representa uma unidade física da empresa.

Campos

Campo	Tipo	Descrição
id	Long	Identificador único
name	String	Nome da unidade
address	String	Endereço completo
status	UnitStatus	Situação da unidade
createdAt	Timestamp	Data de criação
updatedAt	Timestamp	Última atualização

Relacionamentos

Uma unidade possui:

* N Employees
* N Promotions
* N Reports
* N InventoryItems
* N Menus
* N Users

⸻

Employee

Representa um colaborador vinculado a uma unidade.

Campos

Campo	Tipo	Descrição
id	Long	Identificador
unitId	Long	Unidade vinculada
name	String	Nome completo
address	String	Endereço
document	String	CPF ou documento
birthDate	Date	Data de nascimento
type	EmployeeType	Cargo
createdAt	Timestamp	Data de criação
updatedAt	Timestamp	Última atualização

Relacionamentos

Pertence a:

* 1 Unit

Possui:

* 0..1 User
* N Evaluations

Regras

* Documento deve ser único por funcionário.
* Não pode existir funcionário sem unidade.

⸻

Evaluation

Representa avaliações realizadas sobre funcionários.

Campos

Campo	Tipo	Descrição
id	Long	Identificador
employeeId	Long	Funcionário avaliado
rating	Integer	Nota
comment	String	Comentário
createdAt	Timestamp	Data de criação
updatedAt	Timestamp	Última atualização

Relacionamentos

Pertence a:

* 1 Employee

Regras

* Rating entre 1 e 5.
* Comentário opcional.

⸻

User

Representa um usuário autenticável do sistema.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
employeeId	UUID	Funcionário associado
unitId	UUID	Unidade associada
email	String	Email de login
passwordHash	String	Senha criptografada
active	Boolean	Usuário ativo
lastLoginAt	Timestamp	Último login
createdAt	Timestamp	Data de criação
updatedAt	Timestamp	Última atualização

Relacionamentos

Pertence a:

* 1 Employee
* 1 Unit

Possui:

* N Roles
* N AuthSessions
* N AuditLogs

Regras

* Email único.
* Senha armazenada somente em hash.
* Usuário deve estar associado a um funcionário.

⸻

Role

Representa um perfil de acesso.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
name	String	Nome do perfil
description	String	Descrição
createdAt	Timestamp	Data criação
updatedAt	Timestamp	Última atualização

Relacionamentos

Possui:

* N Users
* N Permissions

⸻

Permission

Representa uma permissão granular.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
code	String	Código único
description	String	Descrição
createdAt	Timestamp	Data criação
updatedAt	Timestamp	Última atualização

Relacionamentos

Pertence a:

* N Roles

Exemplo de códigos

UNIT_CREATE
UNIT_UPDATE
EMPLOYEE_CREATE
EMPLOYEE_UPDATE
PRODUCT_CREATE
PRODUCT_UPDATE
PROMOTION_MANAGE
USER_MANAGE

⸻

UserRole

Tabela de associação entre usuários e perfis.

Campos

Campo	Tipo
userId	UUID
roleId	UUID
createdAt	Timestamp

Relacionamentos

* N Users ↔ N Roles

⸻

RolePermission

Tabela de associação entre perfis e permissões.

Campos

Campo	Tipo
roleId	UUID
permissionId	UUID
createdAt	Timestamp

Relacionamentos

* N Roles ↔ N Permissions

⸻

AuthSession

Representa uma sessão autenticada.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
userId	UUID	Usuário
refreshToken	String	Token de renovação
expiresAt	Timestamp	Expiração
revoked	Boolean	Sessão revogada
createdAt	Timestamp	Data criação
updatedAt	Timestamp	Atualização

Relacionamentos

Pertence a:

* 1 User

Regras

* Um refresh token não pode ser reutilizado após revogação.
* Sessões expiradas devem ser invalidadas.

⸻

AuditLog

Registro de auditoria do sistema.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
userId	UUID	Usuário responsável
action	String	Operação realizada
entityName	String	Nome da entidade
entityId	UUID	Registro afetado
oldData	JSON	Estado anterior
newData	JSON	Estado novo
ipAddress	String	IP de origem
createdAt	Timestamp	Data do evento

Relacionamentos

Pertence a:

* 1 User

Exemplo

{
"action": "UPDATE",
"entityName": "Product",
"entityId": "123",
"oldData": {
"price": 10
},
"newData": {
"price": 15
}
}

⸻

Product

Representa um produto comercializado.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
name	String	Nome
price	Decimal	Preço
createdAt	Timestamp	Criação
updatedAt	Timestamp	Atualização

Relacionamentos

Possui:

* N InventoryItems
* N Menus

Regras

* Nome obrigatório.
* Preço maior que zero.

⸻

InventoryItem

Representa o estoque de um produto em uma unidade.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
unitId	UUID	Unidade
productId	UUID	Produto
quantity	Integer	Quantidade atual
minimumQuantity	Integer	Quantidade mínima
createdAt	Timestamp	Criação
updatedAt	Timestamp	Atualização

Relacionamentos

Pertence a:

* 1 Unit
* 1 Product

Regras

* quantity >= 0
* minimumQuantity >= 0

⸻

Menu

Representa um cardápio disponível em uma unidade.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
unitId	UUID	Unidade
updatedMenuAt	Timestamp	Última publicação
createdAt	Timestamp	Criação
updatedAt	Timestamp	Atualização

Relacionamentos

Pertence a:

* 1 Unit

Possui:

* N Products

⸻

MenuProduct

Tabela associativa entre cardápios e produtos.

Campos

Campo	Tipo
menuId	UUID
productId	UUID
createdAt	Timestamp
updatedAt	Timestamp

Relacionamentos

* N Menus ↔ N Products

⸻

Promotion

Representa campanhas promocionais de uma unidade.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
unitId	UUID	Unidade
title	String	Nome da promoção
startDate	Timestamp	Início
endDate	Timestamp	Fim
reward	String	Benefício concedido
createdAt	Timestamp	Criação
updatedAt	Timestamp	Atualização

Relacionamentos

Pertence a:

* 1 Unit

Regras

* startDate deve ser menor que endDate.
* Promoções não podem iniciar no passado (opcional).

⸻

Report

Representa relatórios gerados para uma unidade.

Campos

Campo	Tipo	Descrição
id	UUID	Identificador
unitId	UUID	Unidade
data	JSON	Conteúdo do relatório
createdAt	Timestamp	Criação
updatedAt	Timestamp	Atualização

Relacionamentos

Pertence a:

* 1 Unit

Observação

O campo data deve armazenar um snapshot do relatório gerado, permitindo flexibilidade para diferentes tipos de relatório.

Exemplo:

{
"totalSales": 12000,
"productsSold": 250,
"period": "2026-05"
}

⸻

Resumo dos Relacionamentos

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
User
├── AuthSessions (1:N)
├── AuditLogs (1:N)
└── Roles (N:N)
Role
└── Permissions (N:N)
Menu
└── Products (N:N)
Product
└── InventoryItems (1:N)
Unit
└── InventoryItems (1:N)