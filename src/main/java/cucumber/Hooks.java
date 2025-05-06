package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;

public class Hooks extends Framework{

    public Scenario scenario;
    @Before
    public void setScenario(Scenario _scenario)
    {
        scenario = _scenario;
    }


    @Before
    public void launchBrowser() throws IOException {

        if (getCucumberProperty("browser").equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximised");
            driver = new ChromeDriver(options);
        } else if (getCucumberProperty("browser").equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("start-maximised");
            driver = new FirefoxDriver(options);
        }
    }
        /*@After
        public void closebrowser() throws IOException {
            if(getCucumberProperty("browserclose").equalsIgnoreCase("true")) {
                driver.quit();
            }
        }*/
        @After
        public void takeScreenShotEachStep()
            {
                if(scenario.isFailed()) {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "img/png", "screenshot.png");
                }

            }

    }
