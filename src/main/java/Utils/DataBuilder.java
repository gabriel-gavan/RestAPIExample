package Utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {
	public static JSONObject buildUser() {
		JSONObject bodyBuilder =  new JSONObject();
		Faker faker = new Faker();
		
		String email = faker.internet().emailAddress();
		bodyBuilder.put("name", faker.name().firstName());
		bodyBuilder.put("email",email );
		bodyBuilder.put("age", faker.number().numberBetween(5, 130));
		bodyBuilder.put("gender", "m");
		
		
		return bodyBuilder;
	}
	public static JSONObject buildToDo() {
		JSONObject bodyToDO =  new JSONObject();
		Faker faker = new Faker();
	
		bodyToDO.put("title", faker.book().title());
		bodyToDO.put("body", "Request Body G" );
		
		return bodyToDO;
	}
}
