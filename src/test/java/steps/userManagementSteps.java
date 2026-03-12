package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;
import pages.userManagementPage;

public class userManagementSteps {

    userManagementPage userManagementPage;
    int recordCountBefore;
    public userManagementSteps() {
        userManagementPage = new userManagementPage(Hooks.driver);
    }

    @And("User adds a new record")
    public void addNewRecord(io.cucumber.datatable.DataTable table) throws InterruptedException {
        recordCountBefore = userManagementPage.getNumberOfRecords();
        userManagementPage.clickAddRecord();
        java.util.Map<String, String> row = table.asMaps().get(0);
        userManagementPage.fillNewRecord(row);
        userManagementPage.saveRecord();
    }

    @Then("Validate that the new record is added successfully")
    public void ValidateNewRecordIsAddedSuccessfully() {
        int recordCountAfterAdding = userManagementPage.getNumberOfRecords();
        assertEquals(recordCountBefore + 1, recordCountAfterAdding);
    }
     @Then("User searches for the newly added record with username {string}")
    public void searchForNewRecord(String username) {
         recordCountBefore = userManagementPage.getNumberOfRecords();
        userManagementPage.searchByUsername(username);
    }

     @Then("Validate that the search results display {string} in the username column")
    public void validateSearchResults(String username) {
         String displayedUsername = userManagementPage.getUsernameFromSearchResults();
            assertEquals(username, displayedUsername);
    }

     @Then("User deletes the newly added record")
    public void deleteNewRecord() {
        userManagementPage.deleteRecord();
    }

    @Then("Validate that the number of records is decreased by one after deletion")
    public void validateRecordCountAfterDeletion() {
        int recordCountAfterDeletion = userManagementPage.getNumberOfRecords();
        assertEquals(recordCountBefore - 1, recordCountAfterDeletion);
    }
}
