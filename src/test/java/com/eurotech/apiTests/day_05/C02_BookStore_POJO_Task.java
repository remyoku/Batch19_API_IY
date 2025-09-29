package com.eurotech.apiTests.day_05;

import com.eurotech.apiPOJOTempletes.BookStore.BookStore_BookTemplate;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C02_BookStore_POJO_Task {
    @Test
    public void bookStoreGetBook_POJO_Task() {
        /**
         *
         * Given accept type json
         * When user sends a get request to https://bookstore.toolsqa.com
         * And endPoint:/BookStore/v1/Book
         * Query param ısbn= 9781449365035
         * Then status code should be 200
         * And content type should be application/json; charset=utf-8
         *          *
         * verify that isbn is 9781449365035
         * and verify that title is "Speaking JavaScript"
         * verify that author is "Axel Rauschmayer"
         * verify that publisher is "O'Reilly Media"
         * verify that pages is 460
         */

        String baseUrl = "https://bookstore.toolsqa.com";

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("isbn", "9781449365035")
                .when()
                .get(baseUrl + "/BookStore/v1/Book");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

        response.prettyPrint();

        BookStore_BookTemplate bookStore = response.as(BookStore_BookTemplate.class);

        String isbn = bookStore.getIsbn();
        Assert.assertEquals(isbn,"9781449365035");

        String title = bookStore.getTitle();
        Assert.assertEquals(title,"Speaking JavaScript");

        String author = bookStore.getAuthor();
        Assert.assertEquals(author,"Axel Rauschmayer");

        String publisher = bookStore.getPublisher();
        Assert.assertEquals(publisher,"O'Reilly Media");

        int pages = bookStore.getPages();
        Assert.assertEquals(pages,460);
    }
}
