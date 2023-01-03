package Tests;


import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TemaCurs3RestApi {
	

	@Test
	public void postATodo() throws IOException, ParseException {
		
		
		File fisier =  new File("datacurs3restapi.json");
		
		Response response = RestAssured
				.given()
				    .header("Content-Type", "application/json")
				  
				.when()
					
					.body(fisier)
					.post("https://fakerestapi.azurewebsites.net/api/v1/Books")
					
					
				.then()
					.assertThat().statusCode(200)
				
					.extract().response();
		
		System.out.println(response.asPrettyString());
		String id = response.jsonPath().getString("id");
		System.out.println(id);
		String info = response.jsonPath().getString("title");
		System.out.println(info);
		
		//assertEquals(info, "Todo saved! Nice job!");
	
		
		
		JSONParser parser  = new JSONParser();
		
			FileReader reader =  new FileReader(fisier);
			JSONObject jsonObj =  (JSONObject) parser.parse(reader);
			
			System.out.println(jsonObj);
						
		
		assertTrue(jsonObj.containsValue(id));
	}
	
	}



