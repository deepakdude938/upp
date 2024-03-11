Feature: TS02_Deal_Account_Party


@Regression @TS02 @MonWedFri
Scenario Outline: Create a Deal having Accounts, Parties for EComm Transactions
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSId>".
And Create two Accounts with given "<TSId>"
And Create Parties in the Parties Tab with given "<TSId>"
And Submit the deal
Then Approve the deal from the deal checker
Examples:
      |TSId|
      |TS02|