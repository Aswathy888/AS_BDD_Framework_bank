package bank.stepDefinitions.bank;

import cucumber.Framework;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.Map;
import static org.junit.Assert.assertEquals;


public class Registration extends Framework {

    private static final Logger log = LogManager.getLogger(Registration.class);
    public Scenario scenario;
    String testDataPath;
    String registrationPageObjectPath;

    @Before
    public void setScenario(Scenario _scenario) {
        scenario = _scenario;
        testDataPath = scenario.getUri().toString().replaceAll("/features/.*$", "/").replaceAll(".*/src", "src") + "test_data/testdata.xlsx";
        registrationPageObjectPath = scenario.getUri().toString().replaceAll("/features/.*$", "/").replaceAll(".*/src", "src") + "object_repository/Registration.json";

    }
    String homePageDataSheet = "homePage";
    String registrationPageDataSheet = "registrationPage";

    @Given("^the user navigated to the (.*)")
    public void the_user_navigated_to_the_homepage(String dataset) throws IOException {

        Map<String, String> dataMap = readTestData(dataset, testDataPath, homePageDataSheet);
        driver.get(dataMap.get("HomePage_URL"));
        //Thread.sleep(5000);

    }

    @When("^the user clicks on register button")
    public void theUserClicksOnRegisterButton() throws IOException {
        driver.findElement(getObjectProperty("Registration_link", registrationPageObjectPath)).click();

    }
    @Then("^the user can see the registration page")
    public void theUserCanSeeTheRegistrationPage() throws IOException {
        String actualTitle= driver.getTitle();
        String expectedTitle="ParaBank | Register for Free Online Account Access";
        assertEquals("Page title does not match", expectedTitle, actualTitle);
    }

    @Then("^the user enters their (.*)")
    public void theUserEnterTheirDetails(String dataset) throws IOException, InterruptedException {
        Map<String, String> dataMap = readTestData(dataset, testDataPath, registrationPageDataSheet);

        WebElement firstName = driver.findElement(getObjectProperty("firstName_textBox", registrationPageObjectPath));
        firstName.sendKeys(dataMap.get("firstName"));

        WebElement lastName =driver.findElement(getObjectProperty("lastName_textBox",registrationPageObjectPath));
        lastName.sendKeys(dataMap.get("lastName"));

        WebElement address =driver.findElement(getObjectProperty("address_textBox",registrationPageObjectPath));
        address.sendKeys(dataMap.get("address"));

        WebElement city =driver.findElement(getObjectProperty("city_textBox",registrationPageObjectPath));
        city.sendKeys(dataMap.get("city"));

        WebElement state =driver.findElement(getObjectProperty("state_textBox",registrationPageObjectPath));
        state.sendKeys(dataMap.get("state"));

        WebElement zipcode =driver.findElement(getObjectProperty("zipcode_textBox",registrationPageObjectPath));
        zipcode.sendKeys(dataMap.get("zipcode"));

        WebElement phoneNumber =driver.findElement(getObjectProperty("phoneNumber_textBox",registrationPageObjectPath));
        phoneNumber.sendKeys(dataMap.get("phone"));

        WebElement ssn =driver.findElement(getObjectProperty("ssn_textBox",registrationPageObjectPath));
        ssn.sendKeys(dataMap.get("ssn"));

        WebElement username =driver.findElement(getObjectProperty("username_textBox",registrationPageObjectPath));
        username.sendKeys(dataMap.get("username"));

        WebElement password =driver.findElement(getObjectProperty("password_textBox",registrationPageObjectPath));
        password.sendKeys(dataMap.get("password"));


        WebElement confirm =driver.findElement(getObjectProperty("confirm_textBox",registrationPageObjectPath));
        confirm.sendKeys(dataMap.get("confirm"));

    }


    @When("the user submit")
    public void theUserSubmit() throws IOException {
        driver.findElement(getObjectProperty("register_button",registrationPageObjectPath)).click();
    }


    @Then("the user can see the registration successful message.")
    public void theUserCanSeeTheRegistrationSuccessfulMessage() throws IOException {
        String actualTitle= driver.getTitle();
        String expectedTitle="ParaBank | Customer Created";
        assertEquals("Page title does not match", expectedTitle, actualTitle);
        //driver.findElement(getObjectProperty("",registrationPageObjectPath)).isDisplayed();
    }
}
