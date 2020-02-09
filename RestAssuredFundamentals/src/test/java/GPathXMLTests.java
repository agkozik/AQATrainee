import config.IEndPoint;
import config.TestConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GPathXMLTests extends TestConfig {

    @Test
    public void getFirstGameInTheList() {
        Response response = given()
                .spec(videoGameRequestSpecXML)
                .get(IEndPoint.VIDEOGAMES);
        String name = response.path("videoGames.videoGame.name[0]");
        System.out.println(name);
    }

    @Test
    public void getAttributeName() {
        Response response = given()
                .spec(videoGameRequestSpecXML)
                .get(IEndPoint.VIDEOGAMES);
        String category = response.path("videoGames.videoGame[0].@category");
        System.out.println(category);
    }

    //using restassuredxmlpath Node
    @Test
    public void getListOfXMLNodes() {
        String responseAsString = given()
                .spec(videoGameRequestSpecXML)
                .get(IEndPoint.VIDEOGAMES)
                .asString();
        List<Node> allResults = XmlPath.from(responseAsString)
                .get("videoGames.videoGame.findAll{element -> return element }");
        System.out.println(allResults.get(2).get("name").toString());
    }

    @Test
    public void getListOfXmlNodesByFindAllOnAttribute(){
        String responseAsString = given()
                .spec(videoGameRequestSpecXML)
                .get(IEndPoint.VIDEOGAMES)
                .asString();
        List<Node> allDrivingGames = XmlPath.from(responseAsString)
                .get("videoGames.videoGame.findAll{videoGame -> def category = videoGame.@category; category=='Driving'}");
        System.out.println(allDrivingGames.get(0).get("name").toString());
    }

    @Test
    public void getSingleNode() {
        String responseAsString = given()
                .spec(videoGameRequestSpecXML)
                .get(IEndPoint.VIDEOGAMES)
                .asString();
        Node videoGame=XmlPath
                .from(responseAsString)
                .get("videoGames.videoGame.find{videoGame -> def name = videoGame.name; name=='Tetris'}");
        String videoGameReleaseDate = videoGame.get("releaseDate").toString();
        System.out.println(videoGameReleaseDate);
    }

    @Test
    public void getSingleElementDepthFirst() {
        String responseAsString = given()
                .spec(videoGameRequestSpecXML)
                .get(IEndPoint.VIDEOGAMES)
                .asString();

        int reviewScore = XmlPath
                .from(responseAsString)
                .getInt("**.find{it.name=='Gran Turismo 3'}.reviewScore");
        System.out.println(reviewScore);
    }

    @Test
    public void getAllNodesByConditions(){
        String responseAsString = given()
                .spec(videoGameRequestSpecXML)
                .get(IEndPoint.VIDEOGAMES)
                .asString();

        int reviewScore =90;
        List<Node> allVideoGamesOverCertainScore = XmlPath
                .from(responseAsString)
                .get("videoGames.videoGame.findAll{it.reviewScore.toFloat()>="+reviewScore+"}");
        System.out.println(allVideoGamesOverCertainScore);
    }
}