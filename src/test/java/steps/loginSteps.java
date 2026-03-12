package steps;

import io.cucumber.java.en.*;
import pages.loginPage;

public class loginSteps {

    loginPage loginPage;

     public loginSteps() {
        loginPage = new loginPage(Hooks.driver);
    }

    @When("User navigate to OrangeHRM Page")
    public void navigateToOrangeHRM() {
        Hooks.driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @And("User logs in with username {string} and password {string}")
    public void loginWithCredentials(String username, String password) {
         loginPage.login(username, password);
    }
}