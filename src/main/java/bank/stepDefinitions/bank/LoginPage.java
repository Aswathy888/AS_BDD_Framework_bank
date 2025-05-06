package bank.stepDefinitions.bank;

import cucumber.Framework;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class LoginPage extends Framework {

    public Scenario scenario;
    String testDataPath;
    String loginPageObjectPath;
    @Before
    public void setScenario(Scenario _scenario)
    {
        scenario = _scenario;
        testDataPath = scenario.getUri().toString().replaceAll("/features/.*$","/").replaceAll(".*/src","src")+"test_data/testdata.xlsx";
        loginPageObjectPath = scenario.getUri().toString().replaceAll("/features/.*$","/").replaceAll(".*/src","src")+"object_repository/loginpage.json";

    }

    String testDataSheet = "login_page";
    //public WebDriver driver;

   /* @Given("^I launch the browser")
    public void launchBrowser() throws IOException {

    }*/

    @Then("^I navigate to the (.*)")
    public void navigateToUrl(String dataset) throws InterruptedException, IOException {
       Map<String,String> dataMap = readTestData(dataset,testDataPath,testDataSheet);
      driver.get(dataMap.get("URL"));
     Thread.sleep(5000);



    }
    @Then("^I login with (.*)")
    public void login(String dataset) throws Exception {
        Map<String,String> dataMap = readTestData(dataset,testDataPath,testDataSheet);

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        //By objProperty=getObjectProperty("username_textbox",loginPageObjectPath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(getObjectProperty("username_textbox",loginPageObjectPath)));
        WebElement usernameText=driver.findElement(getObjectProperty("username_textbox",loginPageObjectPath));
        System.out.println(dataMap.get("Username"));

        usernameText.sendKeys(dataMap.get("Username"));
        //usernameText.sendKeys(username);
        WebElement passwordText=driver.findElement(getObjectProperty("password_textbox",loginPageObjectPath));
        passwordText.sendKeys(dataMap.get("Password"));
        WebElement loginButton=driver.findElement(getObjectProperty("login_button",loginPageObjectPath));
        loginButton.click();
        Thread.sleep(5000);
        String homepageTitle =driver.getTitle();
        /*if(homepageTitle.equalsIgnoreCase())
        {
            throw new Exception("Homepage exception");

        }*/

    }


}
