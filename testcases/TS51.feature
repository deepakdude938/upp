Feature: TS51_Scheduled_Split_FixedAmount


@Regression @TS51
Scenario Outline: Scheduled_Split_FixedAmount
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payments in schedule instruction for "<TSID>"
And Create Payment_SubInstruction for "<TSID>"
And Create Payment_SubInstruction for "TS51_1"
And Create Payment_Retry in the scheduled Instructions with given "<TSID>"
And Create Payment_Notification in the scheduled Instructions with given "<TSID>"
And Submit the deal
Then Approve the deal from the deal checker
Then Validate Split Fixed Amount Execution Report "<TSID>"
Examples:
      |TSID|
      |TS51|