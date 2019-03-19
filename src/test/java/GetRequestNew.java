import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequestNew {

	@Test
	public void requestZipCode_checkPlaceNameInResponseBody_expectBeverlyHills() {

		given().log().all().when().get("http://zippopotam.us/us/90210").then()
				.log().body().statusCode(200).and()
				.contentType(ContentType.JSON)
				.body("places[0].state", equalTo("California")).and()
				.body("places.'place name'", hasItem("Beverly Hills"))
				.body("places.'place name'", hasSize(1));

	}
	
}
