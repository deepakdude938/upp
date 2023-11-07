Feature: TS123_Account_Amendment_With_Transaction_Api

@Regression @TS123 @Api
Scenario Outline: Account_Amendment_With_Transaction_Api
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Hit Back button
Then Add Party Basic Details with given "TS123_1".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS123_1".
And Submit the deal
Then Approve the deal from the deal checker
Then Call the Create Transaction Api for "<TSID>"
Then Validate in execution report for scheduled record using endToEndId "<TSID>"
Then Call the Account Ammendment Api "TS123_1"
Then Edit the deal "<TSID>"
Then Verify Account is updated
Then Validate in execution report for scheduled record using endToEndId "TS123_1"

Examples:
      |TSID   |
      |TS123|