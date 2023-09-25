Feature: TS56_Rule_IN_LT_SystemLevel_Assertion

@Regression @TS56 @PaymentProfiles @Assertion
Scenario Outline: Rule_IN_LT_SystemLevel
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch EndToEndId from ODP Record with "<TSID>"
Then Verify in Ecomm Execution Report with given "<TSID>".
And Get the BatchId from Ecomm Payments
And Verify the Pain File For Rule_IN_BT_SystemLevel
#Then Verify Status in Ecomm Execution Report with given "<TSID>".
#And Get the BatchId from payment refID
#And Verify the Pain File For Rule_IN_LT_SystemLevel
Examples:
      |TSID   |
      |TS56   |


