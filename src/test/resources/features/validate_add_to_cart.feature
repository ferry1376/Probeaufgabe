Feature: Validate Add to Cart buttons

  Scenario: Add product to cart after login
    Given I am logged into the application
    When I add the first product to the cart
    Then the cart should display the product