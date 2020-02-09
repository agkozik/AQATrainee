import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTests {

    @BeforeClass
    public static void setup() {
        RestAssured.proxy("localhost", 8888);
    }

    @Test
    public void basicPreemptiveAuthTest() {
        given()
                .auth()
                .preemptive()
                .basic("username", "password")
                .when()
                .get("/someEndpoint");
    }

    @Test
    public void basicChallengedAuthTest() {
        given()
                .auth()
                .basic("username", "password")
                .when()
                .get("/someEndpoint");
    }

    @Test
    public void oAuth1Test() {
        given()
                .auth()
                .oauth("consumerKey", "consumerSecret", "consumerAccessToken", "secretToken")
                .when()
                .get("/someEndpoint");
    }

    @Test
    public void oAuth2Test() {
        given()
                .auth()
                .oauth2("accessToken")
                .when()
                .get("/someEndpoint")
                .then()
                .statusCode(200);
    }

    @Test
    public void sslRelaxedHTTPSTest(){
        given()
                .relaxedHTTPSValidation()
                .when()
                .get("/someEndpoint");
    }

    @Test
    public void keyStoreTest(){
        given()
                .keyStore("/pathToJKS","password")
                .when()
                .get("/someEndpoint");
    }
}