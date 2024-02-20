Feature: TS159_Adhoc_Transaction_FromDeal_Split_DormantAccount(Maker, Checker, Verifier) 

@Assertion @TS159 @Regression
Scenario Outline: TS159_Adhoc_Transaction_FromDeal_Split_DormantAccount_Assertion(Maker, Checker, Verifier) 
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Rejected for all Two Transactions with given "<TSID>"
Examples:
      |TSID |
      |TS159|