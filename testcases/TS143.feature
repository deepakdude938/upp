Feature: TS143_Scheduled payment with 3 Sub Instructions

@Regression @TS143 @Daily
Scenario Outline: Scheduled payment with 3 Sub Instructions
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payment_BasicDetails in the scheduled Instructions with given "<TSID>"
And Create Payment_Schedule in the scheduled Instructions with given "<TSID>"
And Create Payment_SubInstruction in the scheduled Instructions with given "<TSID>"
And Create Payment_SubInstruction in the scheduled Instructions with given "TS143_1"
And Create Payment_SubInstruction in the scheduled Instructions with given "TS143_2"
And Create Payment_Retry in the scheduled Instructions with given "<TSID>"
And Create Payment_Notification in the scheduled Instructions with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Check the Transaction status of all "3" Transactions is Scheduled in execution report with given "<TSID>"
Examples:
      |TSID|
      |TS143|