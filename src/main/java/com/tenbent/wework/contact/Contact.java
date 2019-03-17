package com.tenbent.wework.contact;

import com.tenbent.wework.Restful;
import com.tenbent.wework.Wework;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Contact extends Restful {
    String random=String.valueOf(System.currentTimeMillis());
    public Contact(){
        reset();
    }

    public void reset(){
        requestSpecification=given().log().all().contentType(ContentType.JSON).
                queryParam("access_token", Wework.getToken());
    }
}
