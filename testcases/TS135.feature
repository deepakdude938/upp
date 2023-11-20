Feature: TS135_Account_Amendment_NewAccountwithoutTransaction_Api

@Regression @TS135 @Api
Scenario Outline: Account_Amendment_NewAccountwithoutTransaction_Api
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Hit Back button
Then Add Party Basic Details with given "TS135_1".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS135_1".
Then Add Party Accounts with given "TS135_2".
And Submit the deal
Then Approve the deal from the deal checker
Then Hit NewFourthAccount_Api "<TSID>"

Examples:
      |TSID   |
      |TS135|