Feature: TS161_Adhoc_Transaction_FromDeal_Split_%Balance_Assertion

@Assertion @TS161 @Regression
Scenario Outline: TS161_Adhoc_Transaction_FromDeal_Split_%Balance_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled for all Two Transactions with given "<TSID>"
Examples:
      |TSID |
      |TS161|