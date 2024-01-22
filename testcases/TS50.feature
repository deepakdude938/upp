Feature: TS50_Audit_Transaction

@Regression @TS50
Scenario Outline: Create a UPP Deal with 1 Transaction and check in Audit Transaction
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Create Transaction from Transaction Maker and check whether To Field is accepting Non Registered Beneficiary with given "<TSID>" 
Then Approve the transaction from Transaction Checker with given "<TSID>"
Then Approve the transaction from Transaction Verifier with given "<TSID>"
Then logout of the application
Given Open browser and enter url 
Then Login to the application as "txn_checker"
And verify Audit Transcation with given "<TSID>"
Then verify the downloaded Audit Report with Action version "Create" and "<TSID>"
Examples:
      |TSID|
      |TS50|


