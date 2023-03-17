Feature: TS10


@Regression @TS10
Scenario Outline: Holiday combination
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payments in the Scheduled Instructions with given "<TSID>"
And Create Holiday "<TSID>"
Then Approve Holiday
Examples:
      |TSID|
      |TS10|