import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.myrestassured.basepage.BasePage;


public class AuthorizationGetRequest extends BasePage{
	private static RequestSpecification requestSpec;
	private static ResponseSpecification responseSpec;
	
	
//	@BeforeClass
//	public void baseUri() throws IOException{
//		List<String> Uri=getRequest();
//		
//		
//		requestSpec = new RequestSpecBuilder().setBaseUri(
//				Uri.get(0)).build();
//
//		responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
//				.expectContentType(ContentType.JSON).build();
//		autheticationBasics(Uri.get(1),Uri.get(2));
//	}

	@Test
	public void autheticationBasics(String Username,String password){
		
		given().spec(requestSpec.auth().basic(Username, password))
		.when()
		.get().then().spec(responseSpec).log().all();
		
		
//		RequestSpecification Request = RestAssured.given();
//		Request.auth().basic("ToolsQA", "TestPassword");
//		
//		Response response = Request.get();
//		
//		System.out.println(response.getStatusCode());
	}
}
