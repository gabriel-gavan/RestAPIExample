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
	public static JSONObject buildTodos() {
		
			JSONObject totoBuilder =  new JSONObject();
			Faker faker = new Faker();
		
			totoBuilder.put("title", faker.lordOfTheRings().character());
			totoBuilder.put("body", faker.dune().saying() );
			
			return totoBuilder;
	}
	public static JSONObject buildToken() {
				
				JSONObject body =  new JSONObject();
				
			
				body.put("username", "admin");
				body.put("password", "password123" );
				
				return body;
	}
	public static JSONObject buildBooking() {
		
		/*{
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'*/
		JSONObject booking = new JSONObject();
		booking.put("firstname" , "John");
		booking.put("lastname" ,"Doe");
		booking.put("totalprice" , 111);
		booking.put("depositpaid" , true);
			JSONObject bookingDates = new JSONObject();
			bookingDates.put("checkin" , "2023-01-01");
			bookingDates.put("checkout" , "2023-01-11");
		booking.put("bookingdates" , bookingDates);
		booking.put("additionalneeds" , "Breakfast");
		return booking;
	}
	public static JSONObject buildToken2() {
		
		JSONObject body =  new JSONObject();
		
	
		body.put("user", "admin");
		body.put("pass", "password123" );
		
		return body;
}

}
