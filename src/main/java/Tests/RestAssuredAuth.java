package Tests;

import org.testng.annotations.Test;

import Utils.DataBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class RestAssuredAuth {
	String token;
	int id;
	@Test
	public void getToken() {
	//	https://restful-booker.herokuapp.com/auth
			
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(DataBuilder.buildToken().toJSONString())
				.post("https://restful-booker.herokuapp.com/auth")
				.then()
				.statusCode(200)
				.extract().response();
		System.out.println(resp.asPrettyString());
		token = resp.jsonPath().getString("token");
	}
	@Test(priority=1)
	public void createBooking() {
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(DataBuilder.buildBooking().toJSONString())
				.post("https://restful-booker.herokuapp.com/booking")
				.then()
				.statusCode(200)
				.extract().response();
		System.out.println(resp.asPrettyString());
		
		id = resp.jsonPath().getInt("bookingid");

	}
	@Test(priority=2)
	public void deleteBooking() {
		/*Response resp = given()
				.header("Cookie", "token="+token)
				.contentType(ContentType.JSON)
				.delete("https://restful-booker.herokuapp.com/booking/"+id)
				.then()
				.statusCode(201)
				.extract().response();
				*/
		Response resp = given()
				.auth().preemptive().basic("admin", "password123")
				.delete("https://restful-booker.herokuapp.com/booking/"+id)
				.then()
				.statusCode(201)
				.extract().response();
		System.out.println(resp.asPrettyString());
	}
}
