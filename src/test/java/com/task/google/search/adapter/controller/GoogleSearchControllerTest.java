package com.task.google.search.adapter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoogleSearchControllerTest {

    @Autowired
    private GoogleSearchController controller;

    @Test
    void testGoogle() {
        controller.googleSearch("Are you working?");
        System.out.println();
    }
}