@users
Feature: Users
	
@cadastrarUsuariosSucesso
Scenario: Deve cadastrar usuarios com sucesso preenchendo as informacoes corretamente
	Given que tenha preenchido as informacoes de cadastro
	When cadastrar usuario
	Then deve retornar as informacoes de cadastro
	
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
	When consultar usuarios por pagina
	Then deve retornar lista de usuarios
	