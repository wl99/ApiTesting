package com.tenbent.wework.contact;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    static User user;

    @BeforeAll
    static void setUp(){
        user=new User();
    }

    @Test
    void simplelist() {
    }

    @ParameterizedTest
    @ValueSource(strings = { "sevenriby_", "hogwarts_", "testerhome_"})
    void create(String name) {
        String nameNew=name+user.random;
        String random=user.random.substring(5, 5+8);
        HashMap<String, Object> map=new HashMap<>();
        map.put("userid", nameNew);
        map.put("name", nameNew);
        map.put("department", Arrays.asList(1, 2));
        map.put("mobile", "151"+ random);
        map.put("email", random + "@qq.com");
        user.create(map).then().statusCode(200).body("errcode", equalTo(0));

    }
}