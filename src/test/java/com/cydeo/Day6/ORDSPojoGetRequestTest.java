package com.cydeo.Day6;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
        System.out.println("region1.getLinks().get(0).getRel() = " + region1.getLinks().get(0).getRel());

    }

    @DisplayName("Get request to /employes and only get couple of values as a Pojo class")
    @Test
    public void employeeGet(){
        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(employee1);



    }


    @DisplayName("GET request to region only some field test")
    @Test
    public void regionPojoTest(){
        Regions regions = get("/regions").then().statusCode(200).extract().response().as(Regions.class);

        assertThat(regions.getCount(), is(equalTo(4)));

        List<String> regionNames = new ArrayList<>();
        List<String> regionIds = new ArrayList<>();
    }


}
