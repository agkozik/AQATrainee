package tests;

import config.IEndPoint;
import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static endpoints.EndpointsConstants.VIDEOGAMES;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.CoreMatchers.*;

public class AllTypeRequests  extends TestConfig {

    @Test
    public void getListOfGamesRequestExample(){
        RestAssured.proxy("localhost", 8888);
        RequestSpecification currentRequest = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8080)
                .setBasePath("/app")
                .addHeader("content-type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        given()
                .log()
                .all()
                .when()
                .get(VIDEOGAMES)
                .then()
                .log()
                .ifStatusCodeMatches(not(equalTo(200)))
        .assertThat().statusCode(is(200));
    }

    @Test
    public void getListOfGamesUsingRequestResponseSpecifications() {
        given(videoGameRequestSpecJson)
                .log()
                .all()
                .when()
                .get(VIDEOGAMES)
                .then()
                .log()
                .all();
    }

    @Test
    public void postNewItemIntoGameListStringJson(){
        ValidatableResponse response= given(videoGameRequestSpecJson)
                .when()
                .get(VIDEOGAMES)
                .then();
                int quantity =(response.extract().jsonPath().getList("$").size())+1;
        System.out.println("Количество элементов в массиве: "+quantity);

        String requestJson = "{\n" +
                "  \"id\": "+quantity+",\n" +
                "  \"name\": \"string\",\n" +
                "  \"releaseDate\": \"2020-04-02T08:58:10.426Z\",\n" +
                "  \"reviewScore\": 0,\n" +
                "  \"category\": \"string\",\n" +
                "  \"rating\": \"string\"\n" +
                "}";
        given(videoGameRequestSpecJson)
                .body(requestJson)
                .log()
                .all()
                .when()
                .post(VIDEOGAMES)
                .then()
                .spec(responseSpecification);
    }

    @Test
    public void deleteFromTheDBbyID(int videoGameId){
        given(videoGameRequestSpecJson)
                .when()
                .delete("/videogames/{videoGameId}")
                .then();
    }

}