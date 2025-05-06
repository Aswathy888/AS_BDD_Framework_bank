import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@Smoke"
        ,features = {"src/test/resources"}
        ,plugin = { "pretty",
        "html:reports/basic-cucumber-report/cucumber.html",
        "json:reports/basic-cucumber-report/cucumber.json"}
        ,glue={"bank.stepDefinitions.bank", "cucumber"}
        ,monochrome = false
)

public class TestRunner {


}
