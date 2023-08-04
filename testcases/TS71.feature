Feature: TS71_Budget_Purpose_HalfYearly

@Regression @TS71
Scenario Outline: Budget_Purpose_HalfYearly
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create a Budget for halfYearly with given "<TSID>"
And Create Payment_BasicDetails in the scheduled Instructions with given "<TSID>"
And Create Payment_Schedule in the scheduled Instructions with given "<TSID>"
And Create Payment_SubInstruction in the scheduled Instructions with given "<TSID>"
And Create Payment_SubInstruction in the scheduled Instructions with given "TS70_1"
And Create Payment_Retry in the scheduled Instructions with given "<TSID>"
And Create Payment_Notification in the scheduled Instructions with given "<TSID>"
Then submit the deal
And Create record in ODP "<TSID>"
Then Approve the deal from the deal checker
Then Validate in execution report for scheduled record "<TSID>"
Examples:
      |TSID|
      |TS71|