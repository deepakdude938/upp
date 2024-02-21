Feature: TS157_Adhoc_Transaction_FromDeal

@Regression @TS157
Scenario Outline: Adhoc_Transaction_FromDeal (Maker, Checker, Verifier) 
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then submit the deal
#Then Create odp json payload file with DealId with given "<TSID>"
#And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Create a Transaction from Live Deals with given "<TSID>"
Then Approve the transaction from Transaction Checker with given "<TSID>"
#Then Approve the transaction from Transaction Verifier with given "<TSID>"
And Check the Transaction staus in execution report with given "<TSID>"
Examples:
      |TSID   |
      |TS157  |


