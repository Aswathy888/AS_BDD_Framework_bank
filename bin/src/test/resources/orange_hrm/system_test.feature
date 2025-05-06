@Smoke
Feature: Testcases for orange application.

  Scenario Outline:Validate login
  Validate success and failed logins
    Given I launch the <Browser>
    Then I navigate to the <URL>
    Examples:
      | Browser | URL                                                                |
      | chrome  | https://opensource-demo.orangehrmlive.com/web/index.php/auth/login |