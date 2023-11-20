Feature: TS136_Account_Amendment_NewAccountwithTransaction_Api

@Regression @TS136 @Api
Scenario Outline: Account_Amendment_NewAccountwithTransaction_Api
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Hit Back button
Then Add Party Basic Details with given "TS136_1".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS136_1".
Then Add Party Accounts with given "TS136_2".
And Submit the deal
Then Approve the deal from the deal checker
Then Create Transaction Api for "<TSID>"
Then Create Transaction Api for "TS136_1"
Then Create Transaction Api for "TS136_2"
Then Hit NewFourthAccount_Api "TS135"

Examples:
      |TSID   |
      |TS136|