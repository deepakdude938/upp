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
And Create Payment_SubInstruction in the scheduled Instructions for Budget with given "<TSID>"
And Create Payment_SubInstruction in the scheduled Instructions for Budget with given "TS71_1"
And Create Payment_Retry in the scheduled Instructions with given "<TSID>"
And Create Payment_Notification in the scheduled Instructions with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Check Both Transactions Status is Scheduled "<TSID>"
Examples:
      |TSID|
      |TS71|