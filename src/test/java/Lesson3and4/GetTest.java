package Lesson3and4;
import com.sun.org.apache.xerces.internal.util.PropertyState;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetTest {
    @Test
    @DisplayName("random")
    void randomTest() {
        given()
                .queryParam("apiKey", "d574174118a34a01a89f71701083d4ff")
                .queryParam("limitLicense", "true")
                .queryParam("tags", "occaecat aliqua Duis cupidatat tempor")
                .queryParam("number", "10")
                .when()
                .get("https://api.spoonacular.com/recipes/random")
                .prettyPeek()
                .then()
                .statusCode(200);

    }

    @Test
    @DisplayName("recipesInformation")
    void recipesInformationTest() {
        JsonPath response = given()
                .queryParam("apiKey", "d574174118a34a01a89f71701083d4ff")
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .body()
                .jsonPath();
        assertThat(response.get("vegetarian"), is(false));
        assertThat(response.get("dairyFree"), is(false));
        assertThat(response.get("aggregateLikes"), equelTo("209"));
    }

    private PropertyState equelTo(String s) {
        return null;
    }

    private void assertThat(Object vegetarian, PropertyState propertyState) {
    }

    @Test
    @DisplayName("recipesInformationBulk")
    void recipesInformationBulkTest(){
        given()
                .queryParam("apiKey", "d574174118a34a01a89f71701083d4ff")
                .queryParam("ids", "715538,716429")
                .when()
                .get("https://api.spoonacular.com/recipes/informationBulk")
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("similarRecipes")
    void similarRecipesTest(){
        given()
                .queryParam("apiKey", "d574174118a34a01a89f71701083d4ff")
                .when()
                .get("https://api.spoonacular.com/recipes/715538/similar")
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("autocompleteRecipes")
    void autocompleteRecipesTest() {
        given()
                .queryParam("apiKey", "d574174118a34a01a89f71701083d4ff")
                .queryParam("query", "chick")
                .queryParam("number", "10")
                .when()
                .get("https://api.spoonacular.com/recipes/autocomplete")
                .then()
                .statusCode(200);
    }



}
