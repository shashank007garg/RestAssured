import junit.framework.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PostRequest {

	@BeforeClass
	public void baseUri() {
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
	}

	@SuppressWarnings("unchecked")
	@Test
	public void registrationSuccessfull() {
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification Request = RestAssured.given();
		
	
		// JSONObject is a class that represents a Simple JSON.
		// We can add Key - Value pairs using the put method
		JSONObject requestParams=new JSONObject();
		requestParams.put("FirstName", "Shashank");
		requestParams.put("LastName", "Garg");
		requestParams.put("UserName", "Shashank001");
		requestParams.put("Password", "Sha@123");
		requestParams.put("Email", "Shashank@gmail.com");
		
		//add a header
		Request.header("Content-Type","application/json");
		
		//add the body
		Request.body(requestParams.toJSONString());
		
		Response response = Request.post("/register");
		
		int statusCode=response.getStatusCode();
		
		Assert.assertEquals(statusCode, 201);
		
		String successCode=response.jsonPath().get("SuccessCode");
		
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
		System.out.println(response.asString());
		

	}
}
