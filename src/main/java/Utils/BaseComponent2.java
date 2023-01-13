package Utils;

import org.hamcrest.core.AnyOf;
import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static io.restassured.RestAssured.given;

import org.hamcrest.*;
public class BaseComponent2 {
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	public static RequestSpecification requestSpecToDoGet;
	public static RequestSpecification requestSpecToDoDelete;
	@BeforeClass
	public static void createRequestSpecification() {
		requestSpec = new RequestSpecBuilder().
					//setBaseUri("https://keytrcrud.herokuapp.com/")
					//.setBasePath("api/users/")
				//setBaseUri("https://fakerestapi.azurewebsites.net/")
				//.setBasePath("api/v1/Books/")
				setBaseUri("https://keytodorestapi.herokuapp.com/")
				.setBasePath("api/save/")
					.setContentType(ContentType.JSON)
					.addHeader("accept","application/json")
					.build();
	}
	
	@BeforeClass
		public static void createRequestSpecificationTodoGet() {
			requestSpecToDoGet = new RequestSpecBuilder().
						
					setBaseUri("https://keytodorestapi.herokuapp.com/")
					.setBasePath("api/")
						.setContentType(ContentType.JSON)
						.addHeader("accept","application/json")
						.build();
	}
	@BeforeClass
	public static void createRequestSpecificationTodoDelete() {
		requestSpecToDoDelete = new RequestSpecBuilder().
					
				setBaseUri("https://keytodorestapi.herokuapp.com/")
				.setBasePath("api/delete")
					.setContentType(ContentType.JSON)
					.addHeader("accept","application/json")
					.build();
}
	@BeforeClass
	public static void createResponseSpecification() {
		responseSpec =  new ResponseSpecBuilder()
				.expectStatusCode(anyOf(is(200),is(201)))
				.build();
	}
	public static Response doPostRequest(String body) {

		Response result = 
				given().
					spec(requestSpec).
					body(body).
				when().
					post().
				then().
					spec(responseSpec).
					extract().response();

		return result;

	}
	public static Response doPutRequest(String id, String body) {

		Response result = 
				given().
					spec(requestSpec).
					body(body).
				when().
					put(id).
				then().
					spec(responseSpec).
					extract().response();

		return result;

	}
	public static Response doGetAllRequest() {

		Response result = 
				given().
					spec(requestSpec).
				when().
					get().
				then().
					spec(responseSpec).
					extract().response();

		return result;
	}

	public static Response doDeleteRequest(String id) {

		Response result = 
				given().
				spec(requestSpec).
				when().
					delete(id).
				then().
				spec(responseSpec).
					extract().response();

		return result;
	}
	public static Response doGetOneRequest(String id) {

		Response result = 
				given().
					spec(requestSpec).
				when().
					get(id).
				then().
					spec(responseSpec).
					extract().response();

		return result;
	}
	
	public static Response doGetAllRequestToDo() {

		Response result1 = 
				given().
					spec(requestSpecToDoGet).
				when().
					get().
				then().
					spec(responseSpec).
					extract().response();

		return result1;
	}
	public static Response doDeleteRequestToDo(String id) {

		Response result = 
				given().
				spec(requestSpecToDoDelete).
				when().
					delete(id).
				then().
				spec(responseSpec).
					extract().response();

		return result;
	}
}
