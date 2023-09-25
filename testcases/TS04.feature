Feature: TS04_Linked_Instruction


@Regression @TS04
Scenario Outline: Create deal with Linked Instruction payment
Given Open browser and enter url 
 #userType can be deal_maker, deal_cherk, txn_maker, txn_checker, txn_verifier
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TcId>".
And Create two Accounts with given "<TcId>"
And Create Parties in the Parties Tab with given "<TcId>"
Then create Linked Instruction Payment with given  "<TcId>".
Then Create odp json payload file with DealDetails with given "<TcId>"
And Create record in ODP "<TcId>"
Then approve the deal from the deal checker
Then  Go to execution report and check the status it should be schedule for "<TcId>"
Then Post schedule time ,check the status. it should be awaiting for "<TcId>"
#Then Logout from Application
#Then Login to the application as "txn_maker"
Then Submit the deal to transaction checker
#Then Logout from Application
#Then Login to the application as "txn_checker"
Then Submit the deal to transaction verifier
#Then Logout from Application
#Then Login to the application as "txn_verifier"
#Then Transaction verifier approve deal 
And Check the Transaction staus in execution report with given "<TSID>"



Examples:
      |TcId|
      |TS04|
      