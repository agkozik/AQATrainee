import config.IEndPoint;
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class MyFirstTest extends TestConfig implements IEndPoint {
    @Test
    public void checkStatusCodeByGameId() {
        given()
                .log()
                .ifValidationFails()
                .when()
                .get("videogames/1")
                .then()
                .log()
                .all()
                .spec(responseSpec);
    }
}