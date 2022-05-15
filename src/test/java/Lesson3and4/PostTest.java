package Lesson3and4;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PostTest {
   String id;
    @Test
    @DisplayName("addMeal")
     void addMeal() {
        id = given()
                .queryParam("apiKey", "d574174118a34a01a89f71701083d4ff")
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .body("{\n"
                        + "    \"date\": 1644881179,\n"
                        + "    \"slot\": 1,\n"
                        + "    \"position\": 0,\n"
                        + "    \"type\": \"INGREDIENTS\",\n"
                        + "    \"value\": {\n"
                        + "        \"ingredients\": [\n"
                        + "           {\n"
                        + "             \"name\": \"1 banana\"\n"
                        + "            }\n"
                        + "         ]\n"
                        + "    }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();



    }

    @AfterEach
    void dropDown() {
        given()
                .queryParam("apiKey", "d574174118a34a01a89f71701083d4ff")
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("ClassifyCuisine")
    void classifyCuisine() {
        id = given()
                .queryParam("apiKey", "d574174118a34a01a89f71701083d4ff")
                .body("{\n"
                        + "    \"cuisine\": Mediterranean,\n"
                        + "        \"cuisines\": [\n"
                        + "      {\n"
                        + "             \"Mediterranean,\""
                        + "              \"European,\""
                        + "               \"Italian,\""
                        + "            }\n"
                        + "         ]\n"
                        + "     \"confidence\": 0.0 \n"
                        + "    }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

    }

    @Test
    @DisplayName("Compute Glycemic Load")
    void computeGlycemicLoad() {
        id = given()
                .queryParam("apiKey", "d574174118a34a01a89f71701083d4ff")
                .body("{\n"
                        + "        \"ingredients\": [\n"
                        + "           {\n"
                        + "             \"1 kiwi,\""
                        + "              \"2 cups rice,\""
                        + "               \"2 glasses of water\""
                        + "            }\n"
                        + "         ]\n"
                        + "    }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/food/ingredients/glycemicLoad/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();



    }
}
