Feature: TS159_Adhoc_Transaction_FromDeal_Split_DormantAccount(Maker, Checker, Verifier) 

@Regression @TS159
Scenario Outline: TS159_Adhoc_Transaction_FromDeal_Split_DormantAccount(Maker, Checker, Verifier) 
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Create Two Transaction from Live Deals with given "<TSID>"
Then Approve the transaction from Transaction Checker with given "<TSID>"
Then Approve the transaction from Transaction Verifier with given "<TSID>"
And check Both Transactions Status is Scheduled "<TSID>"
Examples:
      |TSID   |
      |TS159 |


