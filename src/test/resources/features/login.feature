@login
Feature: SecureBank login

  Background:
    Given the user opens the SecureBank login page

  @smoke @negative
  Scenario: Empty credentials are rejected
    When the user submits username "" and password ""
    Then the login page should be displayed
    And a login error or validation message should be displayed

  @negative
  Scenario: Invalid credentials are rejected
    When the user submits username "invalid-user-qa" and password "wrong-password-qa"
    Then the login page should be displayed
    And a login error or validation message should be displayed

  @positive
  Scenario: Configured valid credentials can log in
    When the user submits valid configured credentials
    Then the page should show a post-login view or navigate away from login
