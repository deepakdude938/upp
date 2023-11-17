Feature: TS101_Rule_Without_PaymentInfoDetails_Assertion

@Regression @TS101 @Assertion
Scenario Outline: TS101_Rule_Without_PaymentInfoDetails_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch EndToEndId from ODP Record with "<TSID>"
Then Verify in Ecomm Execution Report with given "<TSID>".
And Get the BatchId from Ecomm Payments
And Verify the AccountNumber Pain File with given "<TSID>".
Examples:
      |TSID|
      |TS101|


