package com.tenbent.wework;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Restful {
    HashMap<String,Object> query=new HashMap<String, Object>();
    public   RequestSpecification requestSpecification=given();
//    public Response send(){
//        requestSpecification=given().log().all();
//        query.forEach((key, value) -> requestSpecification.queryParam(key, value));
//
//        requestSpecification.when().request()
//    }

    public static String template(String path, HashMap<String, Object> map){
        DocumentContext documentContext= JsonPath.parse(Restful.class
                .getResourceAsStream(path));
        map.forEach((key, value) -> documentContext.set(key, value));
        return documentContext.jsonString();
    }
}
