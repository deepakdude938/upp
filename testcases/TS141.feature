Feature: TS141_Payment_with_Retry Failed Scheduled Instruction With Retry_Type Custom

@Regression @TS141 @TuesThurs
Scenario Outline: TS141_Payment_with_Retry Failed Scheduled Instruction with Retry_Type Custom
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create payment_BasicDetails in the scheduled Instructions with given "<TSID>"
And Create payment_Schedule in the scheduled Instructions with given "<TSID>"
And Create payment_SubInstruction in the scheduled Instructions with given "<TSID>"
Then Create Retry Payment with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Check the Transaction staus in execution report with given "<TSID>"
Examples:
      |TSID  |
      |TS141 |


