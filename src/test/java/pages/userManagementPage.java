package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class userManagementPage {

    private WebDriver driver;
    private WebDriverWait wait; 

    private By numberOfRecordsField = By.xpath("//span[@class='oxd-text oxd-text--span']");
    private By addButton = By.xpath("//div[contains(@class,'orangehrm-header-container')]//button[contains(@class,'oxd-button--secondary')]");
    private By userRoleDropdown = By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')][1]");
    private By employeeNameInput = By.xpath("//label[text()='Employee Name']/following::input[1]");
    private By statusDropdown = By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]");
    private By usernameInput = By.xpath("//label[text()='Username']/following::input[1]");
    private By passwordInput = By.xpath("//label[text()='Password']/following::input[1]");
    private By confirmPasswordInput = By.xpath("//label[text()='Confirm Password']/following::input[1]");
    private By saveButton = By.cssSelector("button[type='submit']");
    private By searchUsernameInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By userNameInSearchResults = By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[2]");
    private By deleteButton = By.xpath("(//button[@class='oxd-icon-button oxd-table-cell-action-space'])[1]");
    private By confirmDeleteButton = By.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");

    public userManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
    }

    public int getNumberOfRecords() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfRecordsField));
            String text = element.getText();
            String number = text.replaceAll("[^0-9]", "");
            return Integer.parseInt(number);
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickAddRecord() {
        WebElement addRecordButton = wait.until(ExpectedConditions.elementToBeClickable(addButton));
        addRecordButton.click();
    }

    public void fillNewRecord(java.util.Map<String, String> data) throws InterruptedException {
        if (data.containsKey("User Role")) {
            WebElement role = wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown));
            role.click();
           selectDropdownOption(data.get("User Role"));
        }

        if (data.containsKey("Employee Name")) {
            WebElement employeeName = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameInput));
            employeeName.sendKeys(data.get("Employee Name"));
            Thread.sleep(2000);
             selectDropdownOption(data.get("Employee Name"));
        }

        if (data.containsKey("Status")) {
            WebElement status = wait.until(ExpectedConditions.elementToBeClickable(statusDropdown));
            status.click();
            selectDropdownOption(data.get("Status"));
        }

        if (data.containsKey("Username")) {
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
            username.sendKeys(data.get("Username"));
        }
        if (data.containsKey("Password") || data.containsKey("Confirm Password")) {
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
            password.sendKeys(data.get("Password"));
            WebElement confirmPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordInput));
            confirmPassword.sendKeys(data.get("Confirm Password"));
        }
    }

    private void selectDropdownOption(String visibleText) {
        try {
            By listboxBy = By.xpath("//div[@role='listbox']");
            WebElement listbox = wait.until(ExpectedConditions.visibilityOfElementLocated(listboxBy));
            Thread.sleep(500);

            java.util.List<WebElement> options = listbox.findElements(By.xpath(".//div[@role='option']"));
            for (WebElement opt : options) {
                String txt = opt.getText().trim();
                if (txt.equalsIgnoreCase(visibleText) || txt.contains(visibleText)) {
                    wait.until(ExpectedConditions.elementToBeClickable(opt)).click();
                    return;
                }
            }

            throw new RuntimeException("Option '" + visibleText + "' not found in dropdown");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void saveRecord() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        save.click();
    }
    public void searchByUsername(String username) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchUsernameInput));
        searchInput.clear();
        searchInput.sendKeys(username);
        searchInput.sendKeys(org.openqa.selenium.Keys.ENTER);
    }

    public String getUsernameFromSearchResults() {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInSearchResults));
        return usernameElement.getText().trim();
    }

    public void deleteRecord() {
       WebElement deleteRecordButton = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteRecordButton.click();
        WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmDelete.click();
        driver.get(driver.getCurrentUrl());
        driver.get(driver.getCurrentUrl());
    }
}