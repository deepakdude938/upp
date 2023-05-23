Feature: TS30


@Regression @TS30
Scenario Outline: Rules INITIATION
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two Accounts with given "<TSID>"

Examples:
      |TSID   |
      |TS30   |