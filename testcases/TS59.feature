Feature: TS59

@Regression @TS59
Scenario Outline: Create a Deal having Accounts, Parties
Given Open browser and enter url 
Then Login to the application as "deal_maker1"
And Create new deal with basic details with given "<TSId>".
And Create two Accounts with given "<TSId>"
And Create Parties in the Parties Tab with given "<TSId>"
And Submit the deal
Then Logout from Application
Then Login to the application as "deal_checker1"
Then Approve the deal from the deal checker
Then Logout from Application
Examples:
      |TSId|
      |TS59|