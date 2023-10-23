Feature: TS117_Account_Ammendment_CreditorLookUpKeys_UI

@Regression @TS117
Scenario Outline: Account_Ammendment_CreditorLookUpKeys_UI
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS117_1".
Then Click Document Tab
And Submit the deal
Then Approve the deal from the deal checker
Then Click on Party-Maker Tab "<TSID>"
Then Try to add account with same Creditor look up keys "<TSID>"

Examples:
      |TSID   |
      |TS117|