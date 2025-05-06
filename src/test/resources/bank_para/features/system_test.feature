@Smoke
Feature: Testcases for ParaBank application-registration of a customer.

  Scenario Outline: validate successful registration of a new customer
    Given the user navigated to the <homePage>
    When the user clicks on register button
    Then the user can see the registration page
    And the user enters their <details>
    When the user submit
    Then the user can see the registration successful message.

    Examples:
      | homePage | details |
      | home     | user1   |


