import java.io.IOException;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.myrestassured.basepage.BasePage;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParameterizedGetRequest extends BasePage{

	private static RequestSpecification requestSpec;
	private static ResponseSpecification responseSpec;

	@BeforeClass
	public static void createRequestSpecificatio() throws IOException {
		
		List<String> Uri=getRequest();
		
			requestSpec = new RequestSpecBuilder().setBaseUri(
					Uri.get(0)).build();
	
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

	}

	@DataProvider(name = "zipcode")
	public static Object[][] zipCodesAndPlaces() {

		return new Object[][] {

		{ "us", "90210", "Beverly Hills" }, { "us", "12345", "Schenectady" },
				{ "ca", "B2R", "Waverley" }, { "nl", "1001", "Amsterdam" } };

	}

	@Test(dataProvider = "zipcode")
	public void requestZipCodeCollection_checkPlaceNameInResponseBody_expectPlaceName(
			String... params) {

		given().spec(requestSpec).pathParam("countryCode", params[0])
				.pathParam("zipCode", params[1]).when()
				.get("{countryCode}/{zipCode}").then().spec(responseSpec).and()
				.body("places[0].'place name'", equalTo(params[2])).log().all();
	}
	

}
