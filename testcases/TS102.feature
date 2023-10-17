Feature: TS102_Account_Ammendment

@Api @TS102 @Regression
Scenario Outline: Account_Ammendment
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS102_1".
Then Click Document Tab
And Submit the deal
Then Approve the deal from the deal checker
Then Call the Account Ammendment Api "<TSID>"
Then Edit the deal "<TSID>"
Then Verify Account is updated
Examples:
      |TSID   |
      |TS102|