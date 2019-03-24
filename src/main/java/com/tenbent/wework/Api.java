package com.tenbent.wework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Api {
    HashMap<String, Object> query = new HashMap<String, Object>();
    public RequestSpecification getDefaultRequestSpecification(){
        return given().log().all();
    }


    public static String template(String path, HashMap<String, Object> map) {
        DocumentContext documentContext = JsonPath.parse(Api.class
                .getResourceAsStream(path));
        map.forEach((key, value) -> documentContext.set(key, value));
        return documentContext.jsonString();
    }


    public Response templateFromYaml(String path, HashMap<String, Object> map) {
        //fixed: 根据yaml生成接口定义并发送

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Restful restful = mapper.readValue(WeworkConfig.class.getResourceAsStream(path), Restful.class);

            if (restful.method.toLowerCase().contains("get")) {
                map.forEach((key, value) -> {
                    restful.query.replace(key, value.toString());
                });
            }
            if (restful.method.equalsIgnoreCase("post")) {
                if(map.containsKey("_body")){
                    restful.body=map.get("_body").toString();
                }
                if(map.containsKey("_file")){
                    String filePath=map.get("_file").toString();
                    map.remove("_file");
                    restful.body=template(filePath,map);
                }
            }
            RequestSpecification requestSpecification=getDefaultRequestSpecification();

            if(restful.query!=null){
                restful.query.forEach(requestSpecification::queryParam);
            }

            if(restful.body!=null){
                requestSpecification.body(restful.body);
            }


            return requestSpecification.log().all()
                    .request(restful.method, restful.url)
                    .then().log().all().extract().response();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
