package com.tenbent.wework.contact;

import com.jayway.jsonpath.JsonPath;
import com.tenbent.wework.Wework;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Department extends Contact{
    public Response list(String id) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("id", id);
        return templateFromYaml("/api/list.yaml",map);
    }

    public Response create(String name, int parentid) {
        HashMap<String, Object> map=new HashMap<>();
        map.put("_file", "/data/create.json");
        map.put("name", name);
        map.put("parentid", parentid);
        return templateFromYaml("/api/create.yaml",map);
    }

    public Response update(int id, String name, int parentid) {
        HashMap<String, Object> map=new HashMap<>();
        map.put("_file", "/data/update.json");
        map.put("id", id);
        map.put("name",name);
        map.put("parentid", parentid);
        return templateFromYaml("/api/update.yaml",map);
    }

    public Response delete(int id) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("id", id);
        return templateFromYaml("/api/delete.yaml",map);
    }
}
