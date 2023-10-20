Feature: TS114_Account_Ammendment_CreditorLookUpKeys_UI

@Regression @TS114 
Scenario Outline: Account_Ammendment_CreditorLookUpKeys_UI
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS114_1".
Then Click Document Tab
And Submit the deal
Then Approve the deal from the deal checker
Then Edit party CreditorLookUpKeys with given "<TSID>" from Party Maker tab
Then Submit Party with given "<TSID>"
Then Approve the Party with given "<TSID>"
Then Edit the deal "<TSID>"
Then Verify Account is updated



Examples:
      |TSID   |
      |TS114|