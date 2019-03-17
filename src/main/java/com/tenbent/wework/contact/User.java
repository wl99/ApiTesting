package com.tenbent.wework.contact;

import io.restassured.response.Response;

import java.util.HashMap;

public class User extends Contact {
    public Response simplelist(String departmentId){
        reset();
        return requestSpecification
                .param("department_id", departmentId)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all().statusCode(200).extract().response();
    }

    public Response create(HashMap<String, Object> map){
        String body=template("/data/user.json", map);
        reset();
        return requestSpecification.body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().all().extract().response();

    }
}
