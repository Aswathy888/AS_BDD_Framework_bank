@Smoke
Feature: Testcases for ParaBank application-account creation.

  Scenario Outline: validate successful creation of an account for a customer
    Given the customer click on open account link
    When the customer enter the <details>
    And Click on open new account button
    Then an Account should be successfully created
    Examples:
      | details |
      | user1   |
