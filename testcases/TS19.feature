Feature: TS19_Contacts Configuration

  @Regression @TS19 @WeeklyMon
  Scenario Outline: Contacts Configuration
    Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And Create new deal with basic details with given "<TSID>".
    And Create two Accounts with given "<TSID>"
    And Create Parties in the Parties Tab with given "<TSID>"
    And Update contacts in Successful scheduled option in notification tab

    Examples: 
      | TSID |
      | TS19 |
