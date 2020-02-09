package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.junit.BeforeClass;

public class LocalTestsConfig {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/app/";

        //It will need if only you use Fiddler
        RestAssured.proxy("localhost", 8888);

        //ReadHeaders and show JSON instead XML
        RestAssured.requestSpecification= new RequestSpecBuilder()
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        RestAssured.responseSpecification=new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}