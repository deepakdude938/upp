Feature: TS58_Rule_IN_BT_DealLevel

@Regression @TS58 @PaymentProfiles
Scenario Outline: Rule_IN_BT_DealLevel
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create One Account with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS58_1".
Then Add Party Accounts with given "TS58_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Login to UPP through api
And Call the Rule_IN_BT Api with given "<TSID>".
Then Create odp json payload file with EndToEndId with given "<TSID>"
And Create record in ODP "<TSID>"
Examples:
      |TSID   |
      |TS58   |


