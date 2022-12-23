package steps;

import java.util.Locale;

import com.github.javafaker.Faker;

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
	
	String body;
	
	String name;
	
	String job;
	
	String mensagem;
	
	@Given("que tenha preenchido as informacoes de cadastro")
	public void que_tenha_preenchido_as_informacoes_de_cadastro() {
		
		name = faker.name().fullName();
		
		job = faker.job().title();
		
		body = users.body(name, job);
		
	}
		
	@Then("deve retornar as informacoes de cadastro")
	public void deve_retornar_as_informacoes_de_cadastro() {
	    
		Assert.assertEquals(201, response.statusCode());
		
		Assert.assertEquals(name, response.jsonPath().getString("name"));
		
		Assert.assertEquals(job, response.jsonPath().getString("job"));
		
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
	
	@When("consultar usuarios")
	public void consultar_usuarios() {
	    
		response = users.getListUsers();
		
	}
	
	@Then("deve retornar lista de usuarios")
	public void deve_retornar_lista_de_usuarios() {
	    
		Assert.assertEquals(200, response.statusCode());
		
		Assert.assertEquals("12", response.jsonPath().getString("total"));
		
		Assert.assertEquals("George", response.jsonPath().getString("data[0].first_name"));
		
	}





}
