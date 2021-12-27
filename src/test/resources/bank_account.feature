Feature: Can a client use his account ?

  Scenario: Deposit is credited
    Given a bank client that has an existing account
    When he makes a deposit of 100.25 to his account
    Then his account is credited with 100.25

  Scenario: Withdrawal is made
    Given a bank client that has an existing account
    And he has a positive balance of 200.0 on his account
    When he makes a withdrawal of 75.9 from his account
    Then his account has a remained balance of 124.1

  Scenario: Check operations history
    Given a bank client that has an existing account
    When he checks the history of his account
    Then the bank statement is given