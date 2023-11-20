Feature: TS131_Account_Amendment_NewAccountwithSameCreditorLookUpKeys_Api

@Regression @TS131 @Api
Scenario Outline: Account_Amendment_NewAccountwithSameCreditorLookUpKeys_Api
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Hit Back button
Then Add Party Basic Details with given "TS131_1".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS131_1".
And Submit the deal
Then Approve the deal from the deal checker
Then Hit NewAccountwithSameCreditorLookUpKeys_Api "<TSID>"
#Then Edit the deal "<TSID>"
#Then Verify Creditor LookUp keys are updated "<TSID>"

Examples:
      |TSID   |
      |TS131|