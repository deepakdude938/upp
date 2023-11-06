Feature: TS124_Account_Amendment_UpdateCreditorLookUpKeys_Api

@Regression @TS124 @Api
Scenario Outline: Account_Amendment_UpdateCreditorLookUpKeys_Api
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Hit Back button
Then Add Party Basic Details with given "TS124_1".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS124_1".
And Submit the deal
Then Approve the deal from the deal checker
Then Hit AccountAmmendment_UpdateCreditorLookUpKeys_Api "<TSID>"
Then Edit the deal "<TSID>"
Then Verify Creditor LookUp keys are updated "<TSID>"

Examples:
      |TSID   |
      |TS124|