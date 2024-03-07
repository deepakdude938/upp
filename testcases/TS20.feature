Feature: TS20_Payment Retention Surplus


@Regression @TS20 @Daily
Scenario Outline: Payment Retention Surplus
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payment-Surplus in the Scheduled Instructions with given "<TSID>"
And Create Retention-Surplus with given "<TSID>"
And Create Surplus in the Scheduled Instructions with given "<TSID>"
And Submit the deal
Then Approve the deal from the deal checker
Then Validate SubInstruction Type as "Payment" and "Surplus" and "Retention" for "<TSID>"
Examples:
      |TSID|
      |TS20|