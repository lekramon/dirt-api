package com.dirt.api.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"com.dirt.api.cucumber"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"}
)
public class CucumberRunner {
}
