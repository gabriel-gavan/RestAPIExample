package Tests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static Utils.HasCargoCapacity.iscargocapacity;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import static Utils.NumberChecker.numberOnly;
import static io.restassured.RestAssured.given;
import Utils.BaseComponent2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Tema2Curs8RestApi {
	@Test
	public void checkstuff() {
	Response resp = given ().
		get("https://swapi.dev/api/starships/3/").
		then().
		statusCode(200).extract().response();
	System.out.println(resp.asString());
	JsonPath jsonpath = resp.jsonPath();
		
	//verificam daca nava a aparut fie in filmul https://swapi.dev/api/films/2/" sau //https://swapi.dev/api/films/6/" sau https://swapi.dev/api/films/5/”

	List<String> movies = jsonpath.getList("films");
	List<String> films	= new ArrayList<> (movies);
	assertThat(films, either(hasItem("https://swapi.dev/api/films/2/")).or(hasItem("https://swapi.dev/api/films/6/")).or(hasItem("https://swapi.dev/api/films/5/")));
	        
	// verificam daca diameter se apropie de valoarea 1000 cu o marja de eroare de max 30
	
	
	String length = jsonpath.getString("length");
	assertThat(Integer.parseInt(length), both(is(greaterThan(1000))).and(is(lessThan(1030))));
	
	//verificam daca pilots este o colectie goala a clasei String
	List<String> pilot = jsonpath.getList("pilots");
	List<String> pilots	= new ArrayList<> (pilot);
	assertThat (pilots, is(emptyCollectionOf(String.class)));
	
	//verificam daca films NU este o colectie goala a clasei String
	assertThat (films, is(not(emptyCollectionOf(String.class))));
	
	//verificam daca model contine atat numele Imperial cat si Destroyer
	
	String model = jsonpath.getString("model");
	assertThat(model,both(containsString("Imperial")).and(containsString("Destroyer")));
	
	//Folosind un custom matcher verificam daca nava are cargo capacity.
	
	String cargo_capacity = jsonpath.getString("cargo_capacity");
	assertThat(Integer.parseInt(cargo_capacity), is(iscargocapacity()));
}
}
