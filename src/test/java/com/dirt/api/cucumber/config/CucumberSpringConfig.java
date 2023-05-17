package com.dirt.api.cucumber.config;


import com.dirt.api.DirtApiApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = DirtApiApplication.class)
public class CucumberSpringConfig {
}
