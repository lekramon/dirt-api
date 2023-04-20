package com.dirt.api.adapter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldControllerTest {

    private final HelloWorldController helloWorldController = new HelloWorldController();

    @Test
    public void shouldReturnHelloWorldAndStatusOk() {
        ResponseEntity<String> expectedResponse = helloWorldController.helloWorld();

        assertEquals(HttpStatus.OK, expectedResponse.getStatusCode());
        assertEquals("Hello World! Sippar caminhões", expectedResponse.getBody());
    }

}