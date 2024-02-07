Feature: TS60_Paymentlevel_Rule_IN_LT_DealLeavel_Assertion

@Regression @TS60 @PaymentProfiles
Scenario Outline: _Paymentlevel_Rule_IN_LT_DealLeavel
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch EndToEndId from ODP Record with "<TSID>"
Then Verify in Ecomm Execution Report with given "<TSID>".
And Get the BatchId from Ecomm Payments
And Verify the Pain File For Rule_IN_LT_DealLevel
Examples:
      |TSID   |
      |TS60   |


