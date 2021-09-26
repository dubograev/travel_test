package tests.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SearchAPITests {

    @Test
    void searchForHotelApiTest() {

        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"PageSize\": 25,\n" +
                        "  \"PageNumber\": 0,\n" +
                        "  \"OrderBy\": \"PriceAsc\",\n" +
                        "  \"HotelId\": null\n" +
                        "}")
                .when()
                .post("https://intourist.ru/search/api/TourSearch/GroupByHotel/k1tnp3vw")
                .then()
                .assertThat().body("Results[1].Attributes[0].Name", equalTo("Прокат авто / скутера"));

        //System.out.println(res.asString());
    }
 }
