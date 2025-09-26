package com.eurotech.apiTests.day_05;

import com.eurotech.apiPOJOTempletes.EuroTechAllUser.Education;
import com.eurotech.apiPOJOTempletes.EuroTechAllUser.EuroTechAllUser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class C04_EuroTechGetAllUser_POJO {

    @BeforeClass
    public void setUpClass() {
        baseURI = "https://sdettest.eurotechstudy.eu";
    }

    @Test
    public void pojoTest() {
        /**
         * Class Task
         * Given accept type JSON
         * and Query parameter value pagesize 10
         * and Query parameter value page 1
         * When user send GET request to /sw/api/v1/allusers/alluser
         * Then response status code is 200
         * content-type : application/json; charset=UTF-8
         */

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("pagesize", 10)
                .queryParam("page", 1)
                .when()
                .get("/sw/api/v1/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

       // response.prettyPrint();

        EuroTechAllUser[] allUsers = response.body().as(EuroTechAllUser[].class);
        System.out.println("allUsers[0].getName() = " + allUsers[0].getName());
        System.out.println("allUsers[1].getName() = " + allUsers[1].getName());

        //10.user first education school name

        String tenthSchool = allUsers[9].getEducation().get(0).getSchool();
        System.out.println("tenthSchool = " + tenthSchool);

        //6.user all education
        List<Education> sixthEducation = allUsers[5].getEducation();
        System.out.println("sixthEducation = " + sixthEducation);



    }
}
