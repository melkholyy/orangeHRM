package steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    // @After
    // public void closeBrowser() {
    //     if (driver != null) {
    //         driver.quit();
    //     }
    // }
}