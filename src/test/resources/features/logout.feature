Feature: Logout process

  Scenario: Successful logout process
    Given the user is on the products page after login
    When the user clicks the menu button
    And the user clicks the logout button
    Then the user should be redirected to the login page
