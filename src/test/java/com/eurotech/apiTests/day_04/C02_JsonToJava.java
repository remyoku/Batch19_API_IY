package com.eurotech.apiTests.day_04;

import com.beust.ah.A;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;

public class C02_JsonToJava {
    @BeforeClass
    public void setUpClass() {
        baseURI = "https://sdettest.eurotechstudy.eu";
    }

    @Test
    public void allUserToList() {
        /**
         *
         *          given accept type is json
         *          And query param pagesize is 30
         *          And query param page is 1
         *          And take the request logs
         *          When user sends a get request to "/sw/api/v1/allusers/alluser"
         *          Then status code should be 200
         *          And content type should be application/json; charset=UTF-8
         *
         *          ilk kullanıcını email.nin "afmercan@gmail.com" olduğunu verify edelim
         *          ilk kullanıcını name.nin "MercanS" olduğunu verify edelim
         *          ilk kullanıcının skillerinden ilkinin "PHP" olduğunu verify edelim.
         *          ilk kullanıcının experience.lerinden üçüncüsünün company.sinin "Kraft Techex" olduğunu assert edelim
         *
         *          10. user'ın adını assert edelim "Selim Gezer"
         *          10.user'ın skillerinin ikincisinin Selenium olduğunu verify edelim
         *          10.user'ın educationın 3.sünün school adının Ankara University olduğunu verify edelim
         */
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("pagesize", 30)
                .queryParam("page", 1)
                .when()
                .get("/sw/api/v1/allusers/alluser");

        // response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //json body i java objesine çevirmeye "de-serialization" denir
        //tam tersi de "serialization" olarak geçer.
        //bütün json body sini bir java collection objesine alalrak işlem gerçekleşir

        List<Map<String, Object>> jsonData = response.body().as(List.class);
        System.out.println("jsonData = " + jsonData);

        //java.lang.IllegalStateException: Cannot parse object because no JSON deserializer found in classpath.
        // Please put either Jackson (Databind) or Gson in the classpath.
        //hatasını verir

        //Json serializer kütüphanesi gerekli --> gson dependency

        //ilk kullanıcını email.nin "afmercan@gmail.com" olduğunu verify edelim
        //list        //map
        Object firstEmail = jsonData.get(0).get("email");
        System.out.println("email = " + firstEmail);
        Assert.assertEquals(firstEmail, "afmercan@gmail.com");

        //ilk kullanıcını name.nin "MercanS" olduğunu verify edelim
        Object firstName = jsonData.get(0).get("name");
        System.out.println("name = " + firstName);
        Assert.assertEquals(firstName, "MercanS");

        //ilk kullanıcının skillerinden ilkinin "PHP" olduğunu verify edelim
        List<String> firstUserSkills = (List<String>) jsonData.get(0).get("skills");// ["PHP" "Java"] -->list
        System.out.println("firstUserSkills = " + firstUserSkills);
        String firstSkill = firstUserSkills.get(0);
        Assert.assertEquals(firstSkill, "PHP");

        // ilk kullanıcının experience.lerinden üçüncüsünün company.sinin "Kraft Techex" olduğunu assert edelim
        List<Map<String, Object>> firstExperiences = (List<Map<String, Object>>) jsonData.get(0).get("experience");
        String actualCompany = (String) firstExperiences.get(2).get("company");
        Assert.assertEquals(actualCompany, "Kraft Techex");

        // 10. user'ın adını assert edelim "Selim Gezer"
        String tenthUserName = (String) jsonData.get(9).get("name");
        Assert.assertEquals(tenthUserName, "Selim Gezer");

        // 10.user'ın skillerinin ikincisinin Selenium olduğunu verify edelim
        List<String> tenthSkills = (List<String>) jsonData.get(9).get("skills");
        String secondSkill = tenthSkills.get(1);
        Assert.assertEquals(secondSkill, "Selenium");

        // 10.user'ın educationın 3.sünün school adının Ankara University olduğunu verify edelim
        List<Map<String, Object>> tenthEdu = (List<Map<String, Object>>) jsonData.get(9).get("education");
        String tenthSchool = (String) tenthEdu.get(2).get("school");
        Assert.assertEquals(tenthSchool, "Ankara University");

    }

    @Test
    public void jsonToJava_Map() {
        /**
         Given accept type json
         When user sends a get request to https://bookcart.azurewebsites.net/api
         And endPoint:/Book/{bookId} ---> bookId =3
         Then status code should be 200
         And content type should be application/json; charset=utf-8
         And the book id should be 1
         And the title should be Harry Potter and the Prisoner of Azkaban
         And the category should be Romance
         And the price should be 213.00

         */
        String bookCartBaseUrl = "https://bookcart.azurewebsites.net";

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 3)
                .when()
                .get(bookCartBaseUrl + "/api/Book/{id}");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        response.prettyPrint();

        System.out.println("*******De-Serialize*******");
        //de-serialize
        Map<String, Object> jsonData = response.body().as(Map.class);
        System.out.println("jsonData = " + jsonData);
        System.out.println("jsonData.get(\"bookId\") = " + jsonData.get("bookId"));

        // And the book id should be 1
        double id = (double) jsonData.get("bookId");
        System.out.println("id = " + id);
        Assert.assertEquals(id, 3);

        //And the title should be Harry Potter and the Prisoner of Azkaban
        String title = (String) jsonData.get("title");
        System.out.println("title = " + title);
        Assert.assertEquals(title, "Harry Potter and the Prisoner of Azkaban");

        //And the category should be Romance
        String category = (String) jsonData.get("category");
        System.out.println("category = " + category);
        Assert.assertEquals(category, "Romance");

        //And the price should be 213.00
        double price = (double) jsonData.get("price");
        System.out.println("price = " + price);
        Assert.assertEquals(price, 213.00);


    }

    @Test
    public void bookStore_jsonToJava_Task() {
        /**
         Given accept type json
         When user sends a get request to URI= https://bookstore.toolsqa.com/BookStore/v1/Books
         Then status code should be 200
         And content type should be application/json; charset=utf-8

         validate with de-serialization
         And the first book isbn should be 9781449325862
         And the 7th book publisher should be No Starch Press
         */
    }

    @Test
    public void bookStore_NegativeCase_Task() {
        /**
         * url :https://bookstore.toolsqa.com/Account/v1/User/{UUID}
         *
         * validate with deserialization
         *
         * user id should be 1
         * status code : 401
         * content type : application/json; charset=utf-8
         * assert body message and code by using de serialization json to java
         */
    }
}
