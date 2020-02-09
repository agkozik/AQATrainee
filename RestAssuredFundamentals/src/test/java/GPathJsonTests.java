import config.TestConfig;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GPathJsonTests extends TestConfig {

    @Test
    public void extractElementsFromJsonToMap() {
        Response response = given()
                .spec(footballRequestSpec)
                .get("competitions/2000/teams");
        Map<String, ?> allTeamDataForCurrentTeam = response
                .path("teams.find{it.name=='England'}");
        System.out.println(allTeamDataForCurrentTeam);
    }

    @Test
    public void extractSingleValueUsingAnotherValueMethodFind() {
        Response response = given()
                .spec(footballRequestSpec)
                .get("areas");
        String currentCountry = response.path("areas.find{it.id==2022}.name");
        Assert.assertEquals("USA", currentCountry);
    }

    @Test
    public void extractListValuesWithFindAll() {
        Response response = given()
                .spec(footballRequestSpec)
                .get("areas");
        List<String> areasNames = response.path("areas.findAll {it.id>2150}.name");
        Assert.assertTrue(areasNames.contains("Monaco"));
    }

    @Test
    public void extractMaxValue() {
        Response response = given()
                .spec(footballRequestSpec)
                .get("areas");
        String areaValue = response.path("areas.max{it.id}.name");
        System.out.println(areaValue);
    }

    @Test
    public void extractMultipleValuesAndSumThem() {
        Response response = given()
                .spec(footballRequestSpec)
                .get("areas");
        int sumOfareas = response.path("areas.collect{it.id}.sum()");
        System.out.println(sumOfareas);
    }

    @Test
    public void extractMapOfObjectsUsingFindAllAndFind() {
        String parentArea = "Europe";
        String name = "Belarus";

        Response response = given()
                .spec(footballRequestSpec)
                .get("areas");
        Map<String, ?> areas = response
                .path("areas.findAll{it.parentArea=='%s'}.find{it.name=='%s'}",
                        parentArea, name);
        System.out.println(areas);
    }

    @Test
    public void extractMultipleAreas(){
        String parentArea = "Africa";
        Response response = given()
                .spec(footballRequestSpec)
                .get("areas");

        ArrayList<Map<String,?>> allAreasOfCurrentArea = response
                .path("areas.findAll{it.parentArea=='%s'}",parentArea);
        for (Map i:allAreasOfCurrentArea) {
            System.out.println(i);
        }
    }

}