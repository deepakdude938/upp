Feature: TS58_Rule_IN_BT_DealLevel

@Regression @TS58 @PaymentProfiles
Scenario Outline: Rule_IN_BT_DealLevel
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch EndToEndId from ODP Record with "<TSID>"
Then Verify in Ecomm Execution Report with given "<TSID>".
And Get the BatchId from Ecomm Payments
And Verify the Pain File For Rule_IN_BT_DealLevel
Examples:
      |TSID   |
      |TS58   |


