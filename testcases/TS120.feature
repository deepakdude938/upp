Feature: TS120_Account_Ammendment_NewAccount2_UI

@Regression @TS120
Scenario Outline: Account_Ammendment_NewAccount2_UI
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Add Party Accounts with given "<TSID>".
Then Click Document Tab
And Submit the deal
Then Approve the deal from the deal checker


Examples:
      |TSID   |
      |TS120|