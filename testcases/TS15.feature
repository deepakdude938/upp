Feature: TS15_Payment-Surplus


@Regression @TS15
Scenario Outline: Payment-Surplus
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payment-Surplus in the Scheduled Instructions with given "<TSID>"
And Create Surplus in the Scheduled Instructions with given "<TSID>"
Then Approve the deal from the deal checker
Then Validate SubInstruction Type as "Payment" and "Surplus"
Examples:
      |TSID|
      |TS15|