Feature: TS122_Retention_and_Payment_Retention_with_GB_Account

@Regression @TS122
Scenario Outline: TS122_Retention_and_Payment_Retention_with_GB_Account
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
And Create Retention in the Scheduled Instructions with given "<TSID>"
Then Click On Accounts Tab
And Create payment_BasicDetails in the scheduled Instructions with given "TS122_1"
And Create payment_Schedule in the scheduled Instructions with given "TS122_1"
And Create payment_SubInstruction in the scheduled Instructions with given "TS122_1"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
Examples:
      |TSID|
      |TS122|
