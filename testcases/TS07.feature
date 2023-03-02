Feature: TS07


@Regression @TS07
Scenario Outline: Create deal with Linked Instruction payment
Given Open browser and enter url 
 #userType can be deal_maker, deal_cherk, txn_maker, txn_checker, txn_verifier
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TcId>".
And Create two Accounts with given "<TcId>"
And Create 2 Ecoomerce Parties in the Parties Tab with given "<TcId>"
#Then Then approve the deal from the deal checker
#Then Logout from Application
#Then Login to the application as "txn_maker"
#Then Submit the deal to transaction checker
#Then Logout from Application
#Then Login to the application as "txn_checker"
#Then Submit the deal to transaction verifier
#Then Logout from Application
#Then Login to the application as "txn_verifier"
#Then Submit the deal to transaction verifier
Examples:
      |TcId|
      |TS07|