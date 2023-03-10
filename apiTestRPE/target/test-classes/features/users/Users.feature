@users
Feature: Users
	
@cadastrarUsuariosSucesso
Scenario: Deve cadastrar usuarios com sucesso preenchendo as informacoes corretamente
	Given que tenha preenchido as informacoes corretamente
	When cadastrar usuario
	Then deve retornar <status> e as informacoes
	And data de cadastro
	
	Examples: 
    | status |
    | 201    | # scenario
	
@cadastrarUsuariosErro
Scenario: Nao deve cadastrar usuarios sem informar nome
	Given que tenha preenchido as informacoes de cadastro sem nome
	When cadastrar usuario
	Then deve retornar erro de nome invalido
	
@listarUsuariosSucesso
Scenario: Deve listar usuarios com sucesso
	When consultar usuarios
	Then deve retornar lista de usuarios
	
@listarUsuariosSucesso2
Scenario: Deve listar usuarios por pagina com sucesso
	When consultar usuarios por pagina <pagina>
	Then deve retornar lista de usuarios por pagina <pagina>
	
	Examples: 
    | pagina |
    | 1      | # scenario
    | 2      | # scenario
	
@listarUsuariosSucesso3
Scenario: Nao deve listar usuarios por pagina informando pagina invalida
	When consultar usuarios por pagina <pagina>
	Then nao deve retornar lista de usuarios por pagina <pagina>
	
	Examples: 
    | pagina |
    | 10     | # scenario
    
@alterarUsuariosSucesso
Scenario: Deve alterar usuarios preenchendo as informacoes corretamente
	Given que tenha preenchido as informacoes corretamente
	And informado id do usuario
	When alterar usuario
	Then deve retornar <status> e as informacoes 
	And data de alteracao
	
	Examples: 
    | status |
    | 200    | # scenario
    
@alterarUsuariosSucesso2
Scenario: Deve alterar usuarios preenchendo as informacoes
	Given que tenha preenchido as informacoes corretamente
	And informado id do usuario
	When alterar usuario2
	Then deve retornar <status> e as informacoes 
	And data de alteracao
	
	Examples: 
    | status |
    | 200    | # scenario
    
@excluirUsuariosSucesso
Scenario: Deve excluir usuarios informando id
	Given que preenchido id do usuario
	When excluir usuario
	Then deve excluir e retornar status <status>
	
	Examples: 
    | status |
    | 204    | # scenario
    
    
    
    