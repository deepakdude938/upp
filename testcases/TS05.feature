Feature: TS05


@Regression @TS05
Scenario Outline: Budget and Payments
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create a Budget with given "<TSID>"
And Create Payments in the Scheduled Instructions with given "<TSID>"
Then Approve the deal from the deal checker
Examples:
      |TSID|
      |TS05|