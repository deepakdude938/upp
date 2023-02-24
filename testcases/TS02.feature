Feature: TC02


@Regression @TC02
Scenario Outline: Create a Deal having Accounts, Parties for EComm Transactions
Given User is on LoginPage 
Then Login to the application
And Create new deal with basic details with given "<TSId>".
And Create new Account with given "<TSId>"
And Create Parties in the Parties Tab with given "<TSId>"
Examples:
      |TSId|
      |TS02|