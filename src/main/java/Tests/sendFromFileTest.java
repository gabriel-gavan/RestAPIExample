package Tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import Utils.BaseComponent2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class sendFromFileTest extends BaseComponent2 {

	//@Test
	public void testJsonFile() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("data.json"));
		JSONArray todoList = (JSONArray) obj;
		System.out.println(todoList);
		System.out.println(todoList.get(0));
		
		for(Object todo : todoList) {
			JSONObject objTODO = (JSONObject) todo;
			Response resp = doPostRequest(objTODO.toJSONString());
			System.out.println(resp.asString());
		}
	}
	@Test
	public void parseJSon() throws IOException, ParseException {
		//1.parser de json
		JSONParser parser = new JSONParser();
		// 2. incarcam fisierul JSON
		FileReader reader = new FileReader("data2.json");
		//3. parsam fisierul
		Object obj = parser.parse(reader);
		//4. punem continutu in JsonArray
		JSONArray employeeList = (JSONArray) obj;
		
		System.out.println(employeeList);
		//5. luam un obiect json individual din JSONArray
		JSONObject employeeObject = (JSONObject) employeeList.get(0);
		System.out.println(employeeObject);
		JSONObject employeeAttribute = (JSONObject) employeeObject.get("employee");
		System.out.println(employeeAttribute.get("company"));
		
		System.out.println("--------------------");
		
		File jsonFile = new File ("data2.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		System.out.println(jsonPath.getString("[0]"));
		System.out.println(jsonPath.getString("[0].employee.company"));
	}
}
