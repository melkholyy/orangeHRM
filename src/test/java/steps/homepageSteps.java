package steps;

import io.cucumber.java.en.Then;
import pages.homePage;

public class homepageSteps {

    homePage homePage;

    public homepageSteps() {
        homePage = new homePage(Hooks.driver);
    }

    @Then("User clicks on Admin tab on the left side menu")
    public void clickOnAdminTab() {
        homePage.clickOnAdminTab();
    }


}