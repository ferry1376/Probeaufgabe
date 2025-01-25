
Feature: Validate All Add to Cart buttons



  Scenario: Validate all Add to Cart buttons on the products page
    Given I am logged in as "error_user" to validate all products
    When I check all Add to Cart buttons on the products page
    Then all Add to Cart buttons should work correctly