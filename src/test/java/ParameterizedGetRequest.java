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

public class ParameterizedGetRequest extends BasePage {

	@BeforeClass
	public static void createRequestSpecificatio() throws IOException {
		inIt();
	}

	@DataProvider(name = "zipcode")
	public  Object[][] zipCodesAndPlaces() {

		return new Object[][] {

		{ "us", "90210", "Beverly Hills" }, { "us", "12345", "Schenectady" },
				{ "ca", "B2R", "Waverley" }, { "nl", "1001", "Amsterdam" } };

	}

	@Test(dataProvider = "zipcode")
	public void requestZipCodeCollection_checkPlaceNameInResponseBody_expectPlaceName(
			String state,String zipcode,String placeName) {

		given().spec(requestSpec).pathParam("countryCode", state)
				.pathParam("zipCode", zipcode).when()
				.get("{countryCode}/{zipCode}").then().spec(responseSpec).and()
				.body("places[0].'place name'", equalTo(placeName)).log().all();
	}

}
