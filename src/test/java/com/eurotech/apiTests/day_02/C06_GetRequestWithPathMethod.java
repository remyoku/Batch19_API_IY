package com.eurotech.apiTests.day_02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class C06_GetRequestWithPathMethod {
    @BeforeClass
    public void setUpClass() {
        baseURI = "https://sdettest.eurotechstudy.eu";
    }

    @Test
    public void getAllUserPathMethod() {
        /**Class Task
         * Given accept type JSON
         * and Query parameter value pagesize 50
         * and Query parameter value page 1
         * When user send GET request to /allusers/alluser
         * Then response status code is 200
         * And response content type is "application/json; charset=UTF-8"
         * Verify that first id 1
         * verify that first name MercanS
         * verify that last id is 102
         * verify that last name is GHAN
         */

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .and()
                .when().log().all()
                .get("/sw/api/v1/allusers/alluser");
       // response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //Verify that first id 1
        int firstId = response.path("id[0]");
        int firstId_1 = response.path("[0].id");
        Assert.assertEquals(firstId,firstId_1);
        System.out.println("firstId_1 = " + firstId_1);

        // verify that first name MercanS
        String firstName = response.path("name[0]");
        String firstName_1 = response.path("[0].name");
        Assert.assertEquals(firstName,firstName_1);
        System.out.println("firstName = " + firstName);

        //  verify that last id is 102
        int lastId = response.path("id[-1]");
        Assert.assertEquals(lastId,102);
        //  verify that last name is GHAN

        //3.elemanın skillerini alalım
        List<String> thirdPersonSkills = response.path("skills[2]");
        System.out.println("thirdPersonSkills = " + thirdPersonSkills);

        //3.elemanın 2.skills yazdıralım

        String thirdPersonSecondSkill = response.path("skills[2][1]");
        System.out.println("thirdPersonSecondSkill = " + thirdPersonSecondSkill);

        //diğer yol
        System.out.println("response.path(\"[2].skills[1]\") = " + response.path("[2].skills[1]"));

        //3.elemanın 2. education kaydının schoolunu alalım

        System.out.println("response.path(\"education[2].school[1]\") = " + response.path("education[2].school[1]"));
        System.out.println("response.path(\"[2].education[1].school\") = " + response.path("[2].education[1].school"));
        System.out.println("response.path(\"[2].education.school[1]\") = " + response.path("[2].education.school[1]"));
        System.out.println("response.path(\"education[2][1].school\") = " + response.path("education[2][1].school"));

    }
}
