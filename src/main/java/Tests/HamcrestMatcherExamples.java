package Tests;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import Utils.BaseComponent2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HamcrestMatcherExamples {
	@Test
	public void humcrestMatchers() {
		Response resp = given ().
				get("https://swapi.dev/api/planets/1/").
				then().
				statusCode(200).extract().response();
		System.out.println(resp.asString());
		JsonPath jsonpath = resp.jsonPath();
		assertThat(jsonpath.getString("name"),equalTo("Tatooine"));
		String name = jsonpath.getString("name");	
		assertThat(name,is(equalTo("Tatooine")));
		assertThat(name,is("Tatooine"));
		assertThat(resp.asString(), is(instanceOf(String.class)));
		//not
		assertThat(name, is(not("Terra")));
		assertThat(name, is(not(equalTo("Terra"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		//start-with
		assertThat(resp.asString(), startsWith("{\"name\""));
		assertThat(resp.asString(), startsWithIgnoringCase("{\"NAME\""));
		//ends-with
		assertThat(resp.asString(), endsWith("planets/1/\"}"));
		assertThat(resp.asString(), endsWithIgnoringCase("planeTs/1/\"}"));
		//containsString
		assertThat(jsonpath.getString("created"), containsString(":"));
		assertThat(name,containsStringIgnoringCase("TAtOoine"));
		
		List<String> movies = jsonpath.getList("films");
		List<String> films	= new ArrayList<> (movies);
		assertThat(films, contains("https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
		
		assertThat(films, contains(
				startsWith("https"), 
		        endsWith("3/"), 
		        equalTo("https://swapi.dev/api/films/4/"), 
		        startsWith("https://swapi.dev"), 
		        endsWith("films/6/")));
}
}
