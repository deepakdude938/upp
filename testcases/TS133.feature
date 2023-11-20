Feature: TS133_Account_Ammendment_NewAccount2_API

@Regression @TS133 @Api
Scenario Outline: Account_Ammendment_NewAccount2_API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Add Party Accounts with given "<TSID>".
Then Click Document Tab
And Submit the deal
Then Approve the deal from the deal checker
Then Call Account_Ammendment_NewAccount2_API "<TSID>"
Then Edit the deal "<TSID>"
Then Open account list from party
Then Verify Account is created "<TSID>"

Examples:
      |TSID   |
      |TS133|