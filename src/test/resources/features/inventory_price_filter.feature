Feature: Product Inventory Filter

  Scenario: Sort products by price from low to high
    Given I am on the inventory page
    When I apply the "Price: Low to High" filter
    Then the products should be sorted in ascending order of price
