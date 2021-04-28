package getRequest;

import org.testng.annotations.Test;

import org.testng.Assert;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class SomeTestCases {

	@Test
	public void getResponseCode() { 
		Response resp = RestAssured.get("http://api.openweathermap.org/data/2.5/forecast?q=Sydney,AU&appid=8402e4ad5a39085c8c7e0cd4e8329329&units=imperial"); 
		int code = resp.getStatusCode(); 
		System.out.println("Status code is " +code); 
		Assert.assertEquals(code, 200); 
	}

	@Test
	public void getResponse() {
		Response resp = RestAssured.get("http://api.openweathermap.org/data/2.5/forecast?q=Sydney,AU&appid=8402e4ad5a39085c8c7e0cd4e8329329&units=imperial");
		String data = resp.asString();
		System.out.println("Body data is "+data);
		System.out.println("Response time is "+resp.getTime() + " milliseconds"); 
	}


	public static Response responseValidation(String endpoint) {
		RestAssured.defaultParser = Parser.JSON;
		return given().
				headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
				when().
				get(endpoint).
				then().
				contentType(ContentType.JSON).extract().response();
	}

	@Test 
	public void validateResponse() {
		Response response = responseValidation("http://api.openweathermap.org/data/2.5/forecast?q=Sydney,AU&appid=8402e4ad5a39085c8c7e0cd4e8329329&units=imperial");
		String responseValue = response.jsonPath().getString("city.name"); 
		Assert.assertEquals(responseValue, "Sydney");    
	}

	@Test
	public static void getResponseHeaders() {
		System.out.println("The headers in the response are \n"+get("http://api.openweathermap.org/data/2.5/forecast?q=Sydney,AU&appid=8402e4ad5a39085c8c7e0cd4e8329329&units=imperial").
				then().extract().headers());
	}

}