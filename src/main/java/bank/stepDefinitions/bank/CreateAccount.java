package bank.stepDefinitions.bank;

import cucumber.Framework;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CreateAccount extends Framework {

    public Scenario scenario;
    String testDataPath;
    String createAccountPageObjectPath;

    @Before
    public void setScenario(Scenario _scenario) {
        scenario = _scenario;
        testDataPath = scenario.getUri().toString().replaceAll("/features/.*$", "/").replaceAll(".*/src", "src") + "test_data/testdata.xlsx";
        createAccountPageObjectPath = scenario.getUri().toString().replaceAll("/features/.*$", "/").replaceAll(".*/src", "src") + "object_repository/createAccount.json";

    }


    String CreateAccountPageDataSheet = "createAccount";

    @Given("^the customer click on open account link")
    public void theCustomerClickOnOpenAccountLink() throws IOException {

        driver.findElement(getObjectProperty("openAccount_link",createAccountPageObjectPath)).click();


    }


    @When("the customer enter the (.*)")
    public void theCustomerEnterTheDetails(String dataset) throws IOException {
        Map<String, String> dataMap = readTestData(dataset, testDataPath,CreateAccountPageDataSheet);

        Select select =new Select(driver.findElement(getObjectProperty("accountType_dropdown",createAccountPageObjectPath)));
        select.selectByValue(dataMap.get("type"));
        select.selectByValue(dataMap.get("existingAccount"));
    }

    @And("Click on open new account button")
    public void clickOnOpenNewAccountButton() throws IOException {
        driver.findElement(getObjectProperty("openNewAccount_button",createAccountPageObjectPath)).click();
    }

    @Then("an Account should be successfully created")
    public void anAccountShouldBeSuccessfullyCreated() throws IOException {
        driver.findElement(getObjectProperty("successMessage_label",createAccountPageObjectPath)).isDisplayed();
    }
}