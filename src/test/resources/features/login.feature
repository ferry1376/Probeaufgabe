Feature: Login functionality

  Scenario: Valid login with multiple users
    Given I am on the login page
    When I try to log in with the following credentials:
      | username       | password       |
      | standard_user  | secret_sauce   |
      | locked_out_user| secret_sauce   |
      | problem_user   | secret_sauce   |
      | performance_glitch_user | secret_sauce |
      | error_user | secret_sauce |
      | visual_user | secret_sauce |



    Then I should be redirected to the products page









