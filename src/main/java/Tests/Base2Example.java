package Tests;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import Utils.BaseComponent2;
import Utils.DataBuilder;
import io.restassured.response.Response;

public class Base2Example extends BaseComponent2 {
	public String id;
	public String email;
	
	@Test (priority = 1)
	public void postUser() {
		Response result = doPostRequest(DataBuilder.buildUser().toJSONString());
		System.out.println(result.asPrettyString());
		System.out.println(result.toString());
		id = result.jsonPath().getString("result._id");
		email = result.jsonPath().getString("result.email");
		System.out.println(id);
		assertNotNull(result);
	}
	@Test (priority = 2)
	public void getUser() {
		Response result = doGetOneRequest(id);
		assertEquals(email,result.jsonPath().getString("result.email"));
	}
}
