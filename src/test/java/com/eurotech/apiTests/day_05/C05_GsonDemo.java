package com.eurotech.apiTests.day_05;

import com.eurotech.apiPOJOTempletes.BookCart.BookCart_BookTemplate;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class C05_GsonDemo {
    @Test
    public void deSerialization() {
        /**
         * bookCart book body
         {
         "bookId": 2,
         "title": "HP2",
         "author": "JKR",
         "category": "Mystery",
         "price": 235.00,
         "coverFileName": "9d8f4978-0ef8-42d0-873a-4eb583439237HP2.jpg"
         }
         */

        //dönüştürücü Gson
        Gson gson = new Gson();

        String jsonBody = "{\n" +
                "         \"bookId\": 2,\n" +
                "         \"title\": \"HP2\",\n" +
                "         \"author\": \"JKR\",\n" +
                "         \"category\": \"Mystery\",\n" +
                "         \"price\": 235.00,\n" +
                "         \"coverFileName\": \"9d8f4978-0ef8-42d0-873a-4eb583439237HP2.jpg\"\n" +
                "         }";

        System.out.println("jsonBody = " + jsonBody);
        System.out.println("----------------------");

        //de-serialization json --> map
        Map<String,Object> mapBody = gson.fromJson(jsonBody, Map.class);
        System.out.println("mapBody = " + mapBody);

        System.out.println("----------------------");
        //de-serialization json --> custom class (pojo)
        BookCart_BookTemplate bookCart_book = gson.fromJson(jsonBody, BookCart_BookTemplate.class);
        System.out.println("bookCart_book.getCategory() = " + bookCart_book.getCategory());

    }
    @Test
    public void deSerialization_2() {
        /**
         * bookCart book body
         {
         "bookId": 2,
         "title": "HP2",
         "author": "JKR",
         "category": "Mystery",
         "price": 235.00,
         "coverFileName": "9d8f4978-0ef8-42d0-873a-4eb583439237HP2.jpg"
         }
         */

        //dönüştürücü Gson
        Gson gson = new Gson();

        String jsonBody = """
                {
                         "bookId": 2,
                         "title": "HP2",
                         "author": "JKR",
                         "category": "Mystery",
                         "price": 235.00,
                         "coverFileName": "9d8f4978-0ef8-42d0-873a-4eb583439237HP2.jpg"
                         }
                """;

        System.out.println("jsonBody = " + jsonBody);
        System.out.println("----------------------");

        //de-serialization json --> map
        Map<String,Object> mapBody = gson.fromJson(jsonBody, Map.class);
        System.out.println("mapBody = " + mapBody);

        System.out.println("----------------------");
        //de-serialization json --> custom class (pojo)
        BookCart_BookTemplate bookCart_book = gson.fromJson(jsonBody, BookCart_BookTemplate.class);
        System.out.println("bookCart_book.getCategory() = " + bookCart_book.getCategory());
        System.out.println("bookCart_book.getTitle() = " + bookCart_book.getTitle());

    }

    @Test
    public void serialization() {
        //custom java classs to json object
        BookCart_BookTemplate bookCartBook =
                new BookCart_BookTemplate(25,"Kara Kitap","Orhan Pamuk","Realist",255.25,"zzz.jpg");

        System.out.println("bookCartBook = " + bookCartBook);

        //Dönüştürmek için gson objemizi oluşturalım
        Gson gson = new Gson();

        ///java objesini dönüştürücü ile json objesine çevirelim
        String json = gson.toJson(bookCartBook);
        System.out.println("json = " + json);

        BookCart_BookTemplate bookCartBook_1 =new BookCart_BookTemplate();

        bookCartBook_1.setBookId(251);
        bookCartBook_1.setTitle("Karamazof Kardeşler");
        bookCartBook_1.setAuthor("Dostoveyski");
        bookCartBook_1.setCategory("novel");
        bookCartBook_1.setPrice(295.00);
        bookCartBook_1.setCoverFileName("ddd.bnm");

        String json1 = gson.toJson(bookCartBook_1);
        System.out.println("json1 = " + json1);

    }

    @Test
    public void javaMapToJson() {
        //serialization java map --> json  file

        /**
         * id=55
         * name = İrem
         * email = iremyokus@gmail.com
         */
        Map<String,Object> mapData = new HashMap<>();
        mapData.put("id",55);
        mapData.put("name","İrem");
        mapData.put("email","iremyokus@gmail.com");

        System.out.println("mapData = " + mapData);

        Gson gson = new Gson();
        String jsonData = gson.toJson(mapData);
        System.out.println("jsonData = " + jsonData);
    }
}
