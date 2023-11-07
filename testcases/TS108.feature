Feature: TS108_Scheduled_Payment_with GB Account_Split_By_Amount

@Regression @TS108 @ScbRegression
Scenario Outline: TS108_Scheduled_Payment_with GB Account_Split_By_Amount 
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
And Create payment_BasicDetails in the scheduled Instructions with given "<TSID>"
And Create payment_Schedule in the scheduled Instructions with given "<TSID>"
And Create payment_SubInstruction in the scheduled Instructions with given "<TSID>"
And Create payment_SubInstruction in the scheduled Instructions with given "TS108_1"
And Create payment_SubInstruction in the scheduled Instructions with given "TS108_2"
And Create payment_SubInstruction in the scheduled Instructions with given "TS108_3"
And Create payment_SubInstruction in the scheduled Instructions with given "TS108_4"
And Create payment_SubInstruction in the scheduled Instructions with given "TS108_5"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Check the Transaction status of all 6 Transactions is Scheduled in execution report with given "<TSID>"
Examples:
      |TSID  |
      |TS108 |


