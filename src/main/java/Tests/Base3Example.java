package Tests;

import org.testng.annotations.Test;

import Utils.BaseComponent3;
import Utils.DataBuilder;
import io.restassured.response.Response;

public class Base3Example extends BaseComponent3{
	
	String id;
	@Test
	public void createTodo() {
		Response resp  = doRequest("POST", "",DataBuilder.buildTodos().toJSONString() );
		id = resp.jsonPath().getString("id");
		System.out.println(resp.asPrettyString());
	}
	
	@Test(priority=1)
	public void getToDo() {
		Response resp = doRequest("GET", id, "");
		System.out.println(resp.asPrettyString());
	}
	@Test(priority=2)
	public void updateToDo() {
		Response resp = doRequest("PUT", id, DataBuilder.buildTodos().toJSONString());
		System.out.println(resp.asPrettyString());
	}
	@Test(priority=3)
	public void deleteToDo() {
		Response resp = doRequest("DELETE", id,"");
		System.out.println(resp.asPrettyString());
	}
}
