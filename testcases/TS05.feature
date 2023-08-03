Feature: TS05_Budget and Payments


@Regression @TS05
Scenario Outline: Budget and Payments
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create a Budget with given "<TSID>"
And Create Payments in the Scheduled Instructions with given "<TSID>"
And Create payload file for ODP record "<TSID>"
And Submit the deal
And Create record in ODP "<TSID>"
Then Approve the deal from the deal checker
Then Validate in execution report for scheduled record "<TSID>"
Examples:
      |TSID|
      |TS05|