package validation;

import org.junit.Assert;

import io.restassured.response.Response;

public class Validation {
	
	public void validationUsersListForPage(Response response, int page) {
		
		Assert.assertEquals(200, response.statusCode());
		
		Assert.assertEquals(page, response.jsonPath().getInt("page"));
		
		Assert.assertEquals(6, response.jsonPath().getInt("per_page"));
		
		Assert.assertEquals(12, response.jsonPath().getInt("total"));
		
		Assert.assertEquals(2, response.jsonPath().getInt("total_pages"));
		
	}

}
