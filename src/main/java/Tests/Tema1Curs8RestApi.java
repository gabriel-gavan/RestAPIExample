package Tests;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Tema1Curs8RestApi {
	@Test (priority = 1)
	 public void validateSchemaPozitiveCase() {
		 Response result = given().get("https://swapi.dev/api/people/4/")
				 .then()
				 .statusCode(200)
				 .log().all()
				 .extract().response();
		 
		 System.out.println(result.asString());
		 assertThat(result.asString(), matchesJsonSchemaInClasspath("curs8pozitiv.json"));
	 }
	
	@Test (priority = 2)
	 public void validateSchemaNegativeCase() {
		 Response result = given().get("https://swapi.dev/api/people/4/")
				 .then()
				 .statusCode(200)
				 .log().all()
				 .extract().response();
		 
		 System.out.println(result.asString());
		 assertThat(result.asString(), matchesJsonSchemaInClasspath("curs8negative.json"));
	 }
}
