Feature: TS60_Paymentlevel_Rule_IN_LT_DealLeavel

@Regression @TS60 @PaymentProfiles
Scenario Outline: Rule_IN_LT_DealLeavel
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create One Account with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS60_1".
Then Add Party Accounts with given "TS60_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Login to UPP through api
And Call the Rule_IN_LT Api with given "<TSID>" for deal Level.
Then Create odp json payload file with EndToEndId with given "<TSID>"
And Create record in ODP "<TSID>"

Examples:
      |TSID   |
      |TS60   |


