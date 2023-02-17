Feature: TS04


@Regression @TS04
Scenario Outline: Create deal with Linked Instruction payment
Given User is on LoginPage 
Then Login to the application
And Create new deal with basic details with given "<TcId>".
And Create two Accounts with given "<TcId>"
And Create Parties in the Parties Tab with given "<TcId>"
Then create Linked Instruction Payment with given  "<TcId>".
Then approve the deal from the deal checker
Then Logout from Application
Then Login to transaction maker
Then Submit the deal to transaction checker
Then Logout from Application
Then Login to transaction checker
Then Submit the deal to transaction verifier
Then Logout from Application
Then Login to transaction Verifier
Then Submit the deal to transaction verifier
Examples:
      |TcId|
      |TS04|