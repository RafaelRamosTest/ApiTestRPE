package service;

import config.Config;
import io.restassured.response.Response;

public class UsersService {
	
	Config config = new Config();
	
	Service send = new Service();
	
	Response response;
	
	public Response postUsers(String body) {
		
		String urlUsers = "/users";
		
		response = send.postRequest(config.dominio, urlUsers, body);
		
        return response;
		
	}
	
	public Response putUsers(String body, int idUser) {
		
		String urlUsers = "/users/" + idUser;
		
		response = send.putRequest(config.dominio, urlUsers, body);
		
        return response;
		
	}
	
	public Response patchUsers(String body, int idUser) {
		
		String urlUsers = "/users/" + idUser;
		
		response = send.patchRequest(config.dominio, urlUsers, body);
		
        return response;
		
	}
	
	public String body(String name, String job) {
		
		String body = "{\r\n"
				+ "   \"name\":\"" + name + "\",\r\n"
				+ "   \"job\":\"" + job + "\"\r\n"
				+ "}";
		
		return body;
		
	}
	
	public Response getListUsers() {
		
		String urlUsers = "/users";
		
		response = send.getRequest(config.dominio, urlUsers);		
        
        return response;
		
	}
	
	public Response getListUsersForPage(int page) {
		
		String urlUsers = "/users?page=" + page;
		
		response = send.getRequest(config.dominio, urlUsers);		
        
        return response;
		
	}
	
	public Response deleteUsers(int idUser) {
		
		String urlUsers = "/users/" + idUser;
		
		response = send.deleteRequest(config.dominio, urlUsers);
		
        return response;
		
	}

}
