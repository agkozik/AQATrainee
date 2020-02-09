package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class TestConfig {
    public static RequestSpecification videoGameRequestSpecJson;
    public static RequestSpecification videoGameRequestSpecXML;
    public static RequestSpecification footballRequestSpec;

    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setup() {
        //It will need if only you use Fiddler
        RestAssured.proxy("localhost", 8888);

        //for local data with Json
        videoGameRequestSpecJson = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8080)
                .setBasePath("/app/")
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        //for local data with XML
        videoGameRequestSpecXML = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8080)
                .setBasePath("/app/")
                .addHeader("content-type", "application/xml")
                .addHeader("Accept", "application/xml")
                .build();

        //for remote data
        footballRequestSpec = new RequestSpecBuilder()
                .setBaseUri("http://api.football-data.org")
                .setBasePath("/v2/")
                .addHeader("X-Auth-Token", "42009adb06a14b998396c6921f4bcfbd")
                .addHeader("X-Response-Control", "minified")
                .build();

        //Set as default value
        //RestAssured.requestSpecification = videoGameRequestSpec;

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();
        RestAssured.responseSpecification = responseSpec;
    }


}