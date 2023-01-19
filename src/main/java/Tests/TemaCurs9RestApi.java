package Tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import Utils.DataBuilder;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TemaCurs9RestApi {
	String token;
	String id;
	@Test
	public void getTokenTest1() {
	//	https://restful-booker.herokuapp.com/auth
			
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(DataBuilder.buildToken2().toJSONString())
				.post("https://dev-todo.herokuapp.com/api/auth")
				.then()
				.statusCode(200)
				.extract().response();
		System.out.println(resp.asPrettyString());
		token = resp.jsonPath().getString("token");
	String	idtoken =  resp.jsonPath().getString("id");
	}
	@Test(priority=1)
	public void postToDoUnauthorizedTest2() {
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(DataBuilder.buildToDo().toJSONString())
				.post("https://dev-todo.herokuapp.com/api/auth/save")
				.then()
				.statusCode(401)
				.extract().response();
		System.out.println(resp.asPrettyString());
		assertEquals(resp.jsonPath().getString("msg"), "Sorry, you are not authorized");
	//	id = resp.jsonPath().getInt("bookingid");

	}
	
	@Test(priority=2)
	public void postToDoAuthorizedTest3() {
		Response resp = given()
				.header("Token", token)
				.contentType(ContentType.JSON)
				.body(DataBuilder.buildToDo().toJSONString())
				.post("https://dev-todo.herokuapp.com/api/auth/save")
				.then()
				.statusCode(200)
				.extract().response();
		System.out.println(resp.asPrettyString());
		
		id = resp.jsonPath().getString("id");
		System.out.println(id);
			assertNotNull(id);
	}
@Test(priority=3)
	public void deleteToDoUnauthorizedTest4() {
		Response resp = given()
				
				.contentType(ContentType.JSON)
				.delete("https://dev-todo.herokuapp.com/api/auth/delete/"+id)
				.then()
				.statusCode(401)
				.extract().response();
		assertEquals(resp.jsonPath().getString("msg"), "Sorry, you are not authorized");	
		
		System.out.println(resp.asPrettyString());
	}
@Test(priority=4)
public void deleteToDoAuthorizedWrongTokenTest5() {
	Response resp = given()
			.header("Token", "wrongtoken")
			.contentType(ContentType.JSON)
			.delete("https://dev-todo.herokuapp.com/api/auth/delete/"+id)
			.then()
			.statusCode(403)
			.extract().response();
	assertEquals(resp.jsonPath().getString("msg"), "Wrong token");
	
	System.out.println(resp.asPrettyString());
}
@Test(priority=5)
public void deleteToDoAuthorizedCorrectTokenTest5() {
	Response resp = given()
			.header("Token", token)
			.contentType(ContentType.JSON)
			.delete("https://dev-todo.herokuapp.com/api/auth/delete/"+id)
			.then()
			.statusCode(200)
			.extract().response();
	assertEquals(resp.jsonPath().getString("msg"), "Event deleted.");
	
	System.out.println(resp.asPrettyString());
}
}
