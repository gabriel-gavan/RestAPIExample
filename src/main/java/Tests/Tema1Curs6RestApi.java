package Tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import Utils.BaseComponent2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Tema1Curs6RestApi extends BaseComponent2 {

	@Test
	public void allPagebetween1000and2000() {
		Response result = doGetAllRequest();
		//System.out.println(result.asPrettyString());
			
		
		JsonPath jsonpath = result.jsonPath();
				
		List<String> allPagebetween1000and2000 = jsonpath.getList("findAll{it.pageCount >=1000 & it.pageCount<=2000}.title");
		System.out.println(allPagebetween1000and2000);
		assertTrue(allPagebetween1000and2000.size()==11);
	}
}
