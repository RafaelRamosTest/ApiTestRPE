package steps;

import java.util.Locale;

import com.github.javafaker.Faker;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import service.UsersService;

import org.junit.Assert;

import utils.Utils;

public class UsersSteps {
	
	Faker faker = new Faker(new Locale("pt-BR"));
	
	UsersService users = new UsersService();
	
	Utils utils = new Utils();
	
	Response response;
	
	Scenario scenario;
	
	String body;
	
	String name;
	
	String job;
	
	String mensagem;
	
	int idUser;
	
	@Before("@users")
	public void iniciar(Scenario scenario) {
		
		this.scenario = scenario;		
		
	}
	
	@Given("que tenha preenchido as informacoes corretamente")
	public void que_tenha_preenchido_as_informacoes_corretamente() {
		
		name = faker.name().fullName();
		
		job = faker.job().title();
		
		body = users.body(name, job);
		
	}
		
	@Then("deve retornar {int} e as informacoes")
	public void deve_retornar_e_as_informacoes(int status) {
	    
		Assert.assertEquals(status, response.statusCode());
		
		Assert.assertEquals(name, response.jsonPath().getString("name"));
		
		Assert.assertEquals(job, response.jsonPath().getString("job"));
		
	}
	
	@And("data de cadastro")
	public void data_de_cadastro() {
		
		Assert.assertEquals(utils.data(), response.jsonPath().getString("createdAt").substring(0, 10));
		
	}
	
	@Given("que tenha preenchido as informacoes de cadastro sem nome")
	public void que_tenha_preenchido_as_informacoes_de_cadastro_sem_nome() {
	    
		name = "";
		
		job = faker.job().title();
		
		body = users.body(name, job);
		
		mensagem = "Nome inválido";
		
	}
	
	@Then("deve retornar erro de nome invalido")
	public void deve_retornar_erro_de_nome_invalido() {
	    
		Assert.assertEquals(422, response.statusCode());
		
		Assert.assertEquals(mensagem, response.jsonPath().getString("message"));
		
	}

	@When("cadastrar usuario")
	public void cadastrar_usuario() {
	    
		response = users.postUsers(body);
		
	}
	
	@And("informado id do usuario")
	public void informado_id_do_usuario() {
	    
		response = users.getListUsers();
		
		idUser = response.jsonPath().getInt("data[0].id");
		
	}

	@When("alterar usuario")
	public void alterar_usuario() {
	    
		response = users.putUsers(body, idUser);
		
	}
	
	@When("alterar usuario2")
	public void alterar_usuario2() {
		
		response = users.patchUsers(body, idUser);
		
	}
	
	@And("data de alteracao")
	public void data_de_alteracao() {
		
		Assert.assertEquals(utils.data(), response.jsonPath().getString("updatedAt").substring(0, 10));
		
	}
	
	@When("consultar usuarios")
	public void consultar_usuarios() {
	    
		response = users.getListUsers();
		
	}
	
	@Then("deve retornar lista de usuarios")
	public void deve_retornar_lista_de_usuarios() {
		
		int page = 1;
		
		validationUsersListForPage(response, page);
		
		Assert.assertEquals("George", response.jsonPath().getString("data[0].first_name"));
		
	}
	
	@When("consultar usuarios por pagina {int}")
	public void consultar_usuarios_por_pagina(int page) {

		response = users.getListUsersForPage(page);
		
	}
	
	@Then("deve retornar lista de usuarios por pagina {int}")
	public void deve_retornar_lista_de_usuarios_por_pagina(int page) {
		
		validationUsersListForPage(response, page);
		
	}

	@Then("nao deve retornar lista de usuarios por pagina {int}")
	public void nao_deve_retornar_lista_de_usuarios_por_pagina(int page) {

		validationUsersListForPage(response, page);
		
		Assert.assertEquals("[]", response.jsonPath().getString("data"));
		
	}

	@Given("que preenchido id do usuario")
	public void que_preenchido_id_do_usuario() {
	    
		response = users.getListUsers();
		
		idUser = response.jsonPath().getInt("data[0].id");
		
	}
	
	@When("excluir usuario")
	public void excluir_usuario() {
	    
		response = users.deleteUsers(idUser);
		
	}
	
	@Then("deve excluir e retornar status {int}")
	public void deve_excluir_e_retornar_status(int status) {
	    
		Assert.assertEquals(status, response.statusCode());
		
	}
	
	private void validationUsersListForPage(Response response, int page) {
		
		Assert.assertEquals(200, response.statusCode());
		
		Assert.assertEquals(page, response.jsonPath().getInt("page"));
		
		Assert.assertEquals(6, response.jsonPath().getInt("per_page"));
		
		Assert.assertEquals(12, response.jsonPath().getInt("total"));
		
		Assert.assertEquals(2, response.jsonPath().getInt("total_pages"));
		
	}
	
	@After("@users")
	public void finalizar() {
		
		
		
	}



}
