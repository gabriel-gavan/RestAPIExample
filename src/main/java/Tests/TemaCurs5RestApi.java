package Tests;

import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import Utils.BaseComponent;

public class TemaCurs5RestApi extends BaseComponent{

	String id;
	String name;
	
	@Test(priority = 1)
	public void postPassenger() {
		
		JSONObject body =  new JSONObject();
		Faker faker = new Faker();
		
		name = faker.name().firstName();
		body.put("name", name);
		body.put("trips",250 );
		body.put("airline", 1981);
		
		Response response = doPostRequest("v1/passenger/", body.toJSONString(), HttpStatus.SC_OK);
		assertEquals(response.jsonPath().getString("name"), name);
		id = response.jsonPath().getString("_id");
		System.out.println(	"Created passenger with id: " + id + " and name " + name);
	}
	
	@Test(priority = 2)
	public void getPassenger() {
		Response response = doGetRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().get("airline.name").toString(), "[Tarom]");
		System.out.println(	"Read passenger with id: " + id );
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority = 3)
	public void updatePassenger() {
		
		JSONObject body =  new JSONObject();
		body.put("name", name);
		body.put("trips",300 );
		body.put("airline", 1);
		
		Response response = doPutRequest("v1/passenger/"+id, body.toJSONString(), HttpStatus.SC_OK);
		assertEquals(response.jsonPath().getString("message"), "Passenger data put successfully completed.");
		System.out.println(	"Updated passenger with id: " + id);
	}
	@Test(priority = 4)
	public void getUpdatedPassenger() {
		Response response = doGetRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().get("airline.name").toString(), "[Quatar Airways]");
		assertEquals(response.jsonPath().get("trips").toString(), "300");
		assertEquals(response.jsonPath().get("name").toString(), name);
		
		System.out.println(	"Read updated passenger with id: " + id );
		System.out.println(response.asPrettyString());
	}
	@Test(priority = 5)
	public void DeletePassenger() {
		Response response = doDeleteRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().getString("message"), "Passenger data deleted successfully.");
		System.out.println(	"Deleted passenger with id: " + id );
	}
	
}

