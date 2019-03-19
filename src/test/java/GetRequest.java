import java.util.concurrent.TimeUnit;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetRequest {
	
	@BeforeClass
	public void baseUri(){
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	}

	@Test
	public void getWeatherDetails() {
		
		
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the
		// method URL.
		// This will return the Response from the server. Store the response in
		// a variable.
		
		Response response = httpRequest.get("/Hyderabad");
		int statusCode=response.getStatusCode();
		long timeTaken=response.getTimeIn(TimeUnit.MILLISECONDS);
		String statusLine=response.getStatusLine();
		
		// Reader header of a give name. In this line we will get
		 // Header named Content-Type
		String contentType=response.header("Content-Type");
		
		String servertType=response.header("Server");
		
		Headers allHeader=response.headers();
		
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		Assert.assertEquals(contentType, "application/json");
		Assert.assertEquals(servertType, "nginx");
		
				
		for(Header header:allHeader){
			
			System.out.println("Key:"+header.getName()+" Value:"+header.getValue());
		}
	
		//response body
		ResponseBody body=response.getBody();
		
		String bodyAsString=body.asString();
		
		Assert.assertTrue(bodyAsString.contains("Hyderabad"));
		
		//json path
		// First get the JsonPath object instance from the Response interface
		 JsonPath jsonPathEvaluator = response.jsonPath();
		
		 String city=jsonPathEvaluator.get("City");
		 System.out.println(city);
		 Assert.assertEquals(city, "Hyderabad");
		 
		 

	}
	@Test
	public void getWeatherDetailsInvalidCity() {
	
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the
		// method URL.
		// This will return the Response from the server. Store the response in
		// a variable.
		
		Response response = httpRequest.get("/785689523");
		int statusCode=response.getStatusCode();
		
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode, 400);
	

	}

}
