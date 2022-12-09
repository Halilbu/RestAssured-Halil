package com.cydeo.Day2;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

public class SpartanGetRequest {

    String baseUrl = "http://54.167.214.59:8000";


    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //priting response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to do API testing then ?
        //verify status code is 200
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");

    }


    @DisplayName("GET one spartan /api/spartans/3 and verify")
    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseUrl + "/api/spartans/3");

        //verify status code 200
        Assertions.assertEquals(200, response.statusCode());
        //verify content type
        Assertions.assertEquals(response.contentType(), "application/json");
        //verify json body contains Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }


    @DisplayName("GET request to /api/hello")
    @Test
    public void test3() {
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        //verify status code 200
        Assertions.assertEquals(200, response.statusCode());
        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        //verify we have headers named date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        //how to get and header from response using header key ?
        //we use response.header(String headerName) method to get any header value
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println(response.header("Date"));

        //verify content length is 17
        Assertions.assertEquals("17", response.header("Content-Length"));
        //verify body is "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta", response.body().asString());


    }


}
