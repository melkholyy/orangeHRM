Feature: OrangeHRM Admin User Management Test

  Scenario: Add and delete new admin user in OrangeHRM
    Given User navigate to OrangeHRM Page
    And User logs in with username "Admin" and password "admin123"
    When User clicks on Admin tab on the left side menu
    And User adds a new record
    | User Role | Employee Name | Status | Username | Password | Confirm Password |
    | Admin     | Joseph Evans    | Enabled| JosephEvans111 | Password123! | Password123! |
    Then Validate that the new record is added successfully
    When User searches for the newly added record with username "JosephEvans111"
    Then Validate that the search results display "JosephEvans111" in the username column
    When User deletes the newly added record
    Then Validate that the number of records is decreased by one after deletion