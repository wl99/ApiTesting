package com.tenbent.wework.contact;

import com.jayway.jsonpath.JsonPath;
import com.tenbent.wework.Wework;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Department {
    public Response list(String id) {
        return given().log().all()
                .param("access_token", Wework.getToken())
                .param("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all().statusCode(200).extract().response();
    }

    public Response create(String name, int parentid) {
        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json")).
                set("$.name", name).set("$.parentid", parentid).jsonString();

        return given().log().all().contentType(ContentType.JSON).
                queryParam("access_token", Wework.getToken()).
                body(body).
                when().
                post("https://qyapi.weixin.qq.com/cgi-bin/department/create").
                then().log().all().
                extract().response();
    }

    public Response update(int id, String name, int parentid) {
        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json")).
                set("$.id", id).
                set("$.name", name).
                set("$.parentid", parentid).jsonString();

        System.out.println(body);

        return given().log().all().contentType(ContentType.JSON).
                    queryParam("access_token", Wework.getToken()).
                    body(body).
                when().
                    post("https://qyapi.weixin.qq.com/cgi-bin/department/update").
                then().log().all().
                    extract().response();
    }

    public Response delete(int id) {
        return given().log().all().
                queryParam("access_token", Wework.getToken()).
                queryParam("id", id).
                when().
                get("https://qyapi.weixin.qq.com/cgi-bin/department/delete").
                then().log().all().
                extract().response();
    }
}
