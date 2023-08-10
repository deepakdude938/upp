Feature: TS06

@Regression @TS06
Scenario Outline: Traditional XCRO Transaction (Maker, Checker, Verifier) 
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
Then logout of the application
Then Login to the application as "txn_maker"
And Create a Transaction from Transaction Maker with given "<TSID>"
Then logout of the application
Then Login to the application as "txn_checker"
Then Approve the transaction from Transaction Checker with given "<TSID>"
Then logout of the application
Then Login to the application as "txn_verifier"
Then Approve the transaction from Transaction Verifier with given "<TSID>"
And Check the Transaction staus in execution report with given "<TSID>"
Then logout of the application
Examples:
      |TSID   |
      |TS06   |


