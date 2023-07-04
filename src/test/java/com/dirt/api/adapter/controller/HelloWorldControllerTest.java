package com.dirt.api.adapter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldControllerTest {

    private final HelloWorldController helloWorldController = new HelloWorldController();

    @Test
    void shouldReturnHelloWorldAndStatusOk() {
        final ResponseEntity<String> actualResponse = helloWorldController.helloWorld();

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Hello World! Sippar caminhões", actualResponse.getBody());
    }

}