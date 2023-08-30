Feature: TS56_Paymentlevel_Rule_IN_LT

@Regression @TS56 @PaymentProfiles
Scenario Outline: Rule_IN_LT
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create One Account with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS56_1".
Then Add Party Accounts with given "TS56_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Login to UPP through api
And Call the Rule_IN_LT Api with given "<TSID>".
Then Verify Status in Ecomm Execution Report with given "<TSID>".
And Get the BatchId from payment refID
And Verify the Pain File For Rule_IN_LT_SystemLevel
Examples:
      |TSID   |
      |TS56   |