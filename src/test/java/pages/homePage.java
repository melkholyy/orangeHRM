package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class homePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public homePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By adminTabInSideMenu = By.xpath("//span[contains(@class,'oxd-main-menu-item--name') and text()='Admin']");

    public void clickOnAdminTab() {
        WebElement adminMenu = wait.until(ExpectedConditions.elementToBeClickable(adminTabInSideMenu));
        adminMenu.click();
    }
}