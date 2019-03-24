package com.tenbent.wework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeworkConfigTest {

    @Test
    void load() {
        System.out.println(WeworkConfig.getInstance());
    }
}