Feature: TS104_Account_Ammendment_UI

@Api @TS104 @Regression
Scenario Outline: Account_Ammendment_UI
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS104_1".
Then Click Document Tab
And Submit the deal
Then Approve the deal from the deal checker
Then Edit party account with given "<TSID>" from Party Maker tab
Then Submit Party with given "<TSID>"
Then Approve the Party with given "<TSID>"
Then Edit the deal "<TSID>"
Then Verify Account is updated
And Submit the deal
Then Approve the deal from the deal checker
Then Offboard Account from Party Maker "<TSID>"
Then Submit Party with given "<TSID>"
Then Approve the Party with given "<TSID>"
Then Verify Account is offboarded in Party

Examples:
      |TSID   |
      |TS104|