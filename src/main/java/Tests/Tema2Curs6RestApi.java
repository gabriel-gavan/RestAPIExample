package Tests;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Utils.BaseComponent2;
import Utils.DataBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Tema2Curs6RestApi extends BaseComponent2 {
	String id;
	@Test (priority = 1)
	public void postToDo() {
		
		JSONObject body =  new JSONObject();
		body = DataBuilder.buildToDo();
		Response response = doPostRequest(body.toJSONString(), HttpStatus.SC_OK);
		
		id = response.jsonPath().getString("id");
		System.out.println(	"Created To Do with id: " + id +  "  body " + body);
		
		try(FileWriter file = new FileWriter("todo.json")) {
			file.write(body.toJSONString());
	    }catch (IOException e) {
	    	e.printStackTrace();
	    
	    }		
	}
	@Test (priority = 2)
	public void getTodos() {
		File jsonFile = new File("todo.json");
		JsonPath getTitle = JsonPath.from(jsonFile);
		String title1 = getTitle.getString("title").toString();
		System.out.println("The title is: " + title1);
	
		
		Response result = doGetAllRequestToDo();
		//System.out.println(result.asPrettyString());
		
		JsonPath jsonpath = result.jsonPath();	
		
		String getids = jsonpath.getString("find{it.title =='"+title1+"' }._id");
		id = getids;	
		System.out.println("Id is: " + getids);
	
		
		
	
	}
	@Test (priority = 3)
	public void deleteTodo() {
		Response response = doDeleteRequestToDo(id);
		
		assertEquals(response.jsonPath().getString("msg"), "Event deleted.");
	}
}


