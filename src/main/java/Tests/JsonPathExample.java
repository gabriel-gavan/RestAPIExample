package Tests;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import Utils.BaseComponent2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathExample extends BaseComponent2{

	@Test
	public void jsonPathExample() {
		Response result = doGetAllRequest();
		System.out.println(result.asPrettyString());
		assertTrue(result.asString().contains("Favian11"));
		
		//Jsonpath
		JsonPath jsonpath = result.jsonPath();
		//citeste sizul obiectului array
		System.out.println(jsonpath.getString("users.size()"));
		//read index based
		System.out.println(jsonpath.getString("users[0]"));
		//get filed from object
		System.out.println(jsonpath.getString("users[0].name"));
		//get all values
		System.out.println(jsonpath.getString("users.name"));
		System.out.println(jsonpath.getString("users.email"));
		System.out.println(jsonpath.getString("users._id"));
		System.out.println("gender: " + jsonpath.getString("users.gender"));
		
		List<String> allMales = jsonpath.getList("users.findAll{it.gender == 'm'}.gender");
		System.out.println(allMales);
		
		
		List<String> allFemalse = jsonpath.getList("users.findAll{it.gender == 'f'}.gender");
		System.out.println(allFemalse);
		
		List<String> allBobo = jsonpath.getList("users.findAll{it.name == 'Bobo'}.name");
		System.out.println(allBobo);
		//And condition
		
		String user = jsonpath.getString("users.find{it.name == 'Bobo' & it.age == 12}.email");
		System.out.println(user);
		
		
		String user2 = jsonpath.getString("users.find{it.name == 'Bobo' & it.age> 14}.email");
		System.out.println(user);
		
		//Or condition
		List<String> usersNamed = jsonpath.getList("users.findAll{it.name == 'Bobo' | it.name == 'Kathey'}.name");
		System.out.println(usersNamed);
		
		List<String> usersLessthan = jsonpath.getList("users.findAll{it.age<45}._id");
		System.out.println(usersLessthan);
		
	}
}
