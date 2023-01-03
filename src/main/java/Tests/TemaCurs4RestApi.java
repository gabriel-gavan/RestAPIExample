package Tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.core.IsNot;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TemaCurs4RestApi {
	JSONObject body,body2;
	String id;
	@BeforeClass
	public void Setup() {
		RestAssured.baseURI = "https://keytrcrud.herokuapp.com/";
		body = new JSONObject();
		Faker  faker = new Faker();
		String email = faker.internet().emailAddress();
		body.put("name", faker.name().firstName());
		body.put("email",email );
		body.put("age", faker.number().numberBetween(5, 130));
		body.put("gender", "m");
		
	}
	
	
	@Test (priority = 1)
	public void createUser() {
		Response obj = given().
				contentType(ContentType.JSON).
				body(body.toJSONString()).
				
				when().
					post("api/users/").
				then().
					statusCode(201). 
					body("success", equalTo(true)).
					body("msg", equalTo("Successfully added!")).
					log().all().
					extract().response();
					
					id = obj.jsonPath().getString("result._id");
					System.out.println(	"Created user with id: " + id);		
					
	}
	@Test (priority = 2)
	public void getUser(){
		Response response = given().
				get("api/users/"+id).
				then().statusCode(200).
				extract().response();
	System.out.println(	"Displayed user with id: " + response.jsonPath().getString("result._id"));
	
	
	System.out.println(	response.asPrettyString());
	}
	
	
	
	@Test (priority = 3)
	public void deleteUser(){
		given().
		delete("api/users/" + id).
		then().
		statusCode(200)
		.body("success", equalTo(true))
		.body("msg", equalTo("It has been deleted."));
		System.out.println(	"Deleted user with id: " + id);
	}
	
}