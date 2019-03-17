package com.tenbent.wework.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    Department department;

    @BeforeEach
    void setUp() {
        if (department == null) {
            department = new Department();
        }
    }

    @Test
    void list() {
        department.list("").then().statusCode(200).body("department.name[0]", equalTo("风里雨里"));
        department.list("1").then().statusCode(200).body("department.id[0]", equalTo(1));
    }

    @Test
    void create() {
        department.create("Test_01", 2).then().body("errcode", equalTo(0));
        department.create("Test_01", 2).then().body("errcode", equalTo(60008));
    }

    @Test
    void createWithChinese() {
        department.create("测试001", 2).then().body("errcode", equalTo(0));
    }

    @Test
    void update() {
        int id = department.create("Update_Test", 2).then().extract().body().path("id");
        department.update(id, "Test_update_02", 2).then().body("errmsg", equalTo("updated"));
    }

    @Test
    void delete() {
        int id = department.create("Delete_Test", 2).then().extract().body().path("id");
        department.delete(id).then().body("errmsg",equalTo("deleted"));
    }
}