Feature: TS137_Payment_with_Retry Failed Scheduled Instruction

@Regression @TS137 @ScbRegression
Scenario Outline: TS137_Payment_with_Retry Failed Scheduled Instruction
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
Then Create Retry Payment Forvever with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Check the Transaction staus in execution report with given "<TSID>"
Examples:
      |TSID  |
      |TS137 |


