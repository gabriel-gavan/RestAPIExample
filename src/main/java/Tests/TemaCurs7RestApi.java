package Tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import Utils.BaseComponent2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TemaCurs7RestApi extends BaseComponent2 {
	
	@Test
	public void checkLukeSkywalker() {
		Response resp = given ().
	
			get("https://swapi.dev/api/people/1/").
			then().
			statusCode(200).extract().response();
	System.out.println(resp.asString());
	JsonPath jsonpath = resp.jsonPath();
	assertThat(jsonpath.getString("name"),equalTo("Luke Skywalker"));
	assertThat(jsonpath.getString("height"),greaterThan("171"));
	assertThat(jsonpath.getString("height"),lessThan("80"));
	
	String hairColor = jsonpath.getString("hair_color");
	String skinColor = jsonpath.getString("skin_color");
	String eyeColor = jsonpath.getString("eye_color");
	
		// one assert
    List<String> collection = new ArrayList<>(Arrays.asList(hairColor,
            skinColor,
            eyeColor));
       assertThat(collection, containsInAnyOrder("blond", "blue", "fair"));

	
	
	assertThat(jsonpath.getString("birth_year"),containsString("BBY"));
	
	// check species is empty
	List<String> species = jsonpath.getList("species");
	List<String> checkspecies	= new ArrayList<> (species);
	assertThat(checkspecies, is(empty()));
	
	
	// starship si vehicles au acelasi size
	
	List<String> starship = jsonpath.getList("starships");
	List<String> starshipcheck	= new ArrayList<> (starship);
	
	List<String> vehicles = jsonpath.getList("vehicles");
	List<String> vehiclescheck	= new ArrayList<> (vehicles);
		
	assertThat(starshipcheck.size(),equalTo(vehiclescheck.size()));
	
	// starship si vehicles nu sunt la fel
	assertThat(starshipcheck.toString(),not (equalTo(vehiclescheck.toString())));
}
}