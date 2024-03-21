Feature: TS126_Retention_and_Payment_With_Holiday_Action_As_Previous_Business_Day

@Regression @TS126 @ScbRegression @MonWedFri
Scenario Outline: TS126_Retention_and_Payment_With_Holiday_Action_As_Previous_Business_Day
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create Holiday for GB Account "<TSID>"
And Approve Holiday for GB Account with given "<TSID>"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
#And Call the ODP Login Api
#Then Create account in Odp with details from excel sheet with given "<TSID>".
#Then Call the ODP Logout Api
#And Create Account_One From excel sheet with given "<TSID>".
And Create Retention in the Scheduled Instructions with given "<TSID>"
Then Click On Accounts Tab
And Create payment_BasicDetails in the scheduled Instructions with given "TS126_1"
And Create payment_Schedule in the scheduled Instructions with given "TS126_1"
And Create payment_SubInstruction in the scheduled Instructions with given "TS126_1"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Check Both Transactions Status is Scheduled "<TSID>"
Examples:
      |TSID|
      |TS126|
