package Tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationExample {
 @Test 
 public void validateSchema() {
	 Response result = given().get("https://keytrcrud.herokuapp.com/api/users/63a2de22584e3500157d90af")
			 .then()
			 .statusCode(200)
			 .log().all()
			 .extract().response();
	 
	 System.out.println(result.asString());
	 assertThat(result.asString(), matchesJsonSchemaInClasspath("schema.json"));
 }
}
