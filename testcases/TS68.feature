Feature: TS68_Paymentlevel_Rule_IN_LT_PenddingStatus

@Regression @TS68 @PaymentProfiles
Scenario Outline: Rule_IN_LT_PenddingStatus
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create One Account with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS68_1".
Then Add Party Accounts with given "TS68_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Login to UPP through api
And Call the Rule_IN_LT Api with given "<TSID>" for Pendding status.
Then Verify Status in Ecomm Execution Report with given "<TSID>" for Pendding status.
And Get the BatchId from payment refID for Pendding status
And Verify BatchId Status in Ecomm Execution Report with given "<TSID>"
And Call the Rule_IN_LT Api for IBFT  with given "TS68_1" for Pendding status.
Then Verify Status in Ecomm Execution Report with given "<TSID>" for Pendding status.
And Verify BatchId Status in Ecomm Execution Report with given "<TSID>"

Examples:
      |TSID   |
      |TS68   |



