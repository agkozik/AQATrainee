import config.IEndPoint;
import config.TestConfig;
import io.restassured.response.Response;
import jsonClasses.VideoGame;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;


public class VideoGameDBTests extends TestConfig implements IEndPoint {

    @Test
    public void getAllGames() {
        given()
                .spec(videoGameRequestSpecJson)
                .when()
                .get(IEndPoint.VIDEOGAMES)
                .then();
    }

    @Test
    public void createNewGameByJsonString() {
        String gameBodyJson = "{\n" +
                "  \"id\": 12,\n" +
                "  \"name\": \"My Game\",\n" +
                "  \"releaseDate\": \"2020-02-08T10:29:33.550Z\",\n" +
                "  \"reviewScore\": 4.5,\n" +
                "  \"category\": \"Action\",\n" +
                "  \"rating\": \"Nice\"\n" +
                "}";
        given()
                .spec(videoGameRequestSpecJson)
                .body(gameBodyJson)
                .when()
                .post(IEndPoint.VIDEOGAMES)
                .then();
    }

    @Test
    public void createNewVideoGameByXmlString() {
        String gameBodyXml = "<videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "    <id>13</id>\n" +
                "    <name>Resident Luk</name>\n" +
                "    <releaseDate>2005-10-01T00:00:00+03:00</releaseDate>\n" +
                "    <reviewScore>85</reviewScore>\n" +
                "  </videoGame>";
        given()
                .spec(videoGameRequestSpecXML)
                .body(gameBodyXml)
                .when()
                .post(IEndPoint.VIDEOGAMES)
                .then();
    }

    @Test
    public void updateGameUsingPutMethod() {
        String gameBodyJson = "{\n" +
                "  \"id\": 12,\n" +
                "  \"name\": \"My UpdatedGame\",\n" +
                "  \"releaseDate\": \"2020-02-08T10:29:33.550Z\",\n" +
                "  \"reviewScore\": 4.5,\n" +
                "  \"category\": \"Action\",\n" +
                "  \"rating\": \"Nice\"\n" +
                "}";
        given()
                .spec(videoGameRequestSpecJson)
                .body(gameBodyJson)
                .when()
                .put("/videogames/12")
                .then();
    }

    @Test
    public void updateGameUsingPatchMethod() {
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"My PatchedGame\",\n" +
                "  \"releaseDate\": \"2020-02-08T10:29:33.550Z\",\n" +
                "  \"reviewScore\": 4.5,\n" +
                "  \"category\": \"Action\",\n" +
                "  \"rating\": \"Nice\"\n" +
                "}";
        given()
                .spec(videoGameRequestSpecJson)
                .body(gameBodyJson)
                .when()
                .patch("/videogames/11")
                .then();
    }

    @Test
    public void deleteGame() {
        given()
                .spec(videoGameRequestSpecJson)
                .delete("/videogames/14")
                .then();
    }

    @Test
    public void getSingleGame() {
        given()
                .spec(videoGameRequestSpecJson)
                .pathParam("videoGameId", 5)
                .when()
                .get(IEndPoint.SINGLE_VIDEOGAME);
    }

    //Make class and send it as json
    @Test
    public void serializeToJson() {
        VideoGame videoGame = VideoGame.builder()
                .id(15)
                .name("My Favorite Game")
                .category("Musical")
                .rating("Five stars")
                .releaseDate("2020-02-09")
                .reviewScore(89)
                .build();
        given()
                .spec(videoGameRequestSpecJson)
                .body(videoGame)
                .when()
                .post(IEndPoint.VIDEOGAMES)
                .then();
    }

    @Test
    public void schemaXMLValidation() {
        given()
                .spec(videoGameRequestSpecXML)
                .pathParam("videoGameId", 5)
                .when()
                .get(IEndPoint.SINGLE_VIDEOGAME)
                .then()
                .body(matchesXsdInClasspath("VideoGame.xsd"));
    }

    @Test
    public void shemaJsonValidation() {
        given()
                .spec(videoGameRequestSpecJson)
                .pathParam("videoGameId", 5)
                .when()
                .get(IEndPoint.SINGLE_VIDEOGAME)
                .then()
                .body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }

    @Test
    public void jsonToPojoСlass() {
        Response response = given()
                .spec(videoGameRequestSpecJson)
                .pathParam("videoGameId", 5)
                .when()
                .get(IEndPoint.SINGLE_VIDEOGAME);
        VideoGame videoGame = response.getBody().as(VideoGame.class);
        Assert.assertEquals(videoGame.toString(), 6, videoGame.getId());
    }

    @Test
    public void checkResponseTime() {
        given()
                .spec(videoGameRequestSpecJson)
                .pathParam("videoGameId", 4)
                .when()
                .get(IEndPoint.SINGLE_VIDEOGAME)
                .then()
                .time(lessThan(2000L));
    }


}