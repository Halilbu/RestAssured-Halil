package com.cydeo.Day5;

import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("Get one database from api and Database")
    @Test
    public void testDB1(){

        String query = "select spartan_id,name,gender,phone from spartans/n" +
                "where spartan_id = 15";

        //save data inside map

        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);

        //get info from api
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)

                .when()
                .get("/api/spartans/{id}")

                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response();

        Map<String,Object> apiMap = response.as(Map.class);
        System.out.println(apiMap);

        assertThat(apiMap.get("id").toString(), is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"), is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"), is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(), is(dbMap.get("PHONE").toString()));



    }


}
