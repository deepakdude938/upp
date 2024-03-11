Feature: TS103_Adhoc Transaction Regression with GB Account_Assertion

@Assertion @TS103 @Regression @ScbRegression @MonWedFri
Scenario Outline: TS103_Adhoc Transaction Regression with GB Account_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Verify Tnx Status as Triggered or settled for all 3 Transactions with given "<TSID>"
Examples:
      |TSID |
      |TS103|