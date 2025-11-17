package com.booklist.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${DATASOURCE_URL}")
    private String dbUrl;

    @GetMapping("/env-test")
    public String test() {
        return dbUrl;
    }
}
