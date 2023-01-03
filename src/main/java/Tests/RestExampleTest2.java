package Tests;

import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Utils.BaseComponent;
import io.restassured.response.Response;

public class RestExampleTest2 extends BaseComponent{
	String id;
	@Test (priority=1)
	public void postUser() {
		
		JSONObject body = new JSONObject();
		Faker faker = new Faker();
		
		String email = faker.internet().emailAddress();
		body.put("name", faker.name().firstName());
		body.put("email", email);
		
		body.put("age", faker.number().numberBetween(5,130));
		body.put("gender", "m");
		
		Response response = doPostRequest("api/users", body.toJSONString(), HttpStatus.SC_CREATED);
		assertEquals(response.jsonPath().getString("result.email"), email);
		System.out.println(response.jsonPath().getString("result.email"));
		id = response.jsonPath().getString("result_id");
	}
	@Test (priority=2)
	public void getUser() {
		Response response = doGetResponse("api/users/", id, HttpStatus.SC_OK);
		assertEquals(response.jsonPath().get("result._id"), id);
	}
	
	@Test (priority=3)
	public void deleteUser() {
		Response response = doDeleteResponse("api/users/", id, HttpStatus.SC_OK);
		assertEquals(response.jsonPath().get("result._id"), id);
		assertEquals(response.jsonPath().getString("msg"), "It hase been deleted.");
	}
}
