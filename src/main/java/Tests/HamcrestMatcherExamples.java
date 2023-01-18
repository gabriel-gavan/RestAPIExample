package Tests;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static Utils.NumberChecker.numberOnly;
import static Utils.NumberIsPositive.numberPositive;
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
		//verific daca exista un anume item in colectie
		assertThat(films, hasItem("https://swapi.dev/api/films/5/"));
		//verific daca exista mai multe obiecte dar nu toate
		
		assertThat(films, hasItems("https://swapi.dev/api/films/5/","https://swapi.dev/api/films/6/","https://swapi.dev/api/films/1/"));
		
		assertThat (films, hasItem(startsWith("http")));
		
		assertThat (films, hasItems(endsWith("4/"),startsWith("http"),endsWith("/6/"),containsString("swapi")));
		
		assertThat (films, hasSize(5));
		
		assertThat (films, hasSize(lessThan(10)));
		assertThat (films, hasSize(greaterThan(3)));
		
		assertThat (films.get(0), containsString("1"));
		assertThat (films, hasToString(containsString("5")));
		
		assertThat (films, both(hasSize(lessThan(10))).and(hasToString(containsString("https"))));
		films.clear();
		assertThat (films, is(empty()));
		assertThat (films, is(emptyCollectionOf(String.class)));
		
		//array
		String [] array = {"https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"} ;
		
		assertThat(array, is (not(emptyArray())));
		assertThat(array, is (not(nullValue())));
		assertThat(array, arrayContaining("https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));

		assertThat(array, arrayContainingInAnyOrder("https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/6/", 
		        "https://swapi.dev/api/films/5/"));
		//strings in any order
		
		assertThat(resp.asString(), stringContainsInOrder("rotation_period","orbital_period","diameter"));
		
		//pattern
		assertThat(name, matchesPattern("[A-Za-z]+"));
		name = "Tatooine23";
		assertThat(name, matchesPattern("[A-Za-z0-9]+"));
		
		name = "23";
		assertThat(name, matchesPattern("[0-9]+"));

		
		//custom matchers
		String rotation_period = jsonpath.getString("rotation_period");
		String climate = jsonpath.getString("climate");
		String diameter = jsonpath.getString("diameter");
		
		assertThat(rotation_period, numberOnly());
		assertThat(rotation_period, is(numberOnly()));
		
		assertThat(climate, is(not(numberOnly())));
		
		assertThat(Integer.parseInt(diameter), is(numberPositive()));
		
		//AND
		name = "Tatooine";
		assertThat(name,both(containsString("a")).and(containsString("oo")));
		//OR
		assertThat(name,either(is("Tatooine")).or(is("Tatooine123")).or(is("Earth")));
}
}
