Feature: Validate Remove button functionality

  Scenario: Verify Remove buttons for error_user
    Given I am logged in as "error_user"
    When I add all products to the cart
    And I attempt to remove each product from the cart
    Then the Remove buttons should function correctly
