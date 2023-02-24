Feature: TC01


@Regression @TC01
Scenario Outline: Create deal with Linked Instruction payment
Given Open browser and enter url 
 #userType can be deal_maker, deal_cherk, txn_maker, txn_checker, txn_verifier
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TcId>".
And Create two Accounts with given "<TcId>"
Examples:
      |TcId|
      |TS01|
 