import config.IEndPoint;
import config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

public class FootballTests extends TestConfig {
    //query parameter use ?season=YYYY
    @Test
    public void getAllCompetitionsBySeasonNumber(){
        given()
                .spec(footballRequestSpec)
                .queryParam("season",2016)
                .when()
                .get(IEndPoint.FOOTBALL)
                .then();
    }
    @Test
    public void getTeamCount(){
        given()
                .spec(footballRequestSpec)
                .when()
                .get("competitions")
                .then()
                .body("count", equalTo(149));
    }

    @Test
    public void checkFirstTeamName(){
        given()
                .spec(footballRequestSpec)
                .when()
                .get("competitions")
                .then()
                .body("competitions[0].area.name",containsStringIgnoringCase("Africa"));
    }

    @Test
    public void getJson(){
        String responseBody = given()
                .spec(footballRequestSpec)
                .when()
                . get("competitions").asString();
        System.out.println(responseBody);

    }

    @Test
    public void getJsonCheckBefore(){
        Response response = given()
                .spec(footballRequestSpec)
                .when()
                .get("competitions")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
        String jsonResponseAsString = response.asString();
    }

    @Test
    public void extractHeaders(){
        Response response = given()
                .spec(footballRequestSpec)
                .when()
                .get("competitions")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
        Headers headers = response.getHeaders();
        String contentType = response.getContentType();
        System.out.println(contentType);
    }

    @Test
    public void extractFieldFromJson(){
        String numberOfAvailableSeasons =given()
                .spec(footballRequestSpec)
                .when()
                .get(IEndPoint.FOOTBALL_COMPETITIONS)
                .jsonPath().getString("competitions.numberOfAvailableSeasons[2]");
        System.out.println(numberOfAvailableSeasons);
    }

    @Test
    public void parseFieldsToList(){
        Response response = given()
                .spec(footballRequestSpec)
                .get(IEndPoint.FOOTBALL_COMPETITIONS)
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        List<String> countries = response.path("competitions.name");
        for (String i:countries) {
            System.out.println(i);
        }
    }

}