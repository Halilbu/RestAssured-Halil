package com.cydeo.Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath {

    @BeforeAll
    public static void init() {
        baseURI = "http://54.167.214.59:8000";
    }


        /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @DisplayName("GET one spartan with Path Method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("api/spartans/{id}");

        assertEquals(200, response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("    \"id\": 10,\n" +
                "    \"name\": \"Lorenza\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 3312820936"));

    }

}
