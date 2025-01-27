Feature: Successful purchase process

  Scenario: Successful purchase process
    Given the user logs in with valid credentials "standard_user" and "secret_sauce"
    And the user adds an item to the cart
    And the user views the cart
    And the user proceeds to checkout
    And the user enters the details "John" "Doe" "12345"
    And the user finishes the checkout
    Then the checkout should be complete
