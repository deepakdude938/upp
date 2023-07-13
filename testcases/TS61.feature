Feature: TS61_Payment Retention Decimal


@Regression @TS61
Scenario Outline: Payment Retention Decimal
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
#And Create Parties in the Parties Tab with given "<TSID>"
And Create Retention in the Scheduled Instructions with given "<TSID_1>"
And Create Payment-Surplus in the Scheduled Instructions with given "<TSID>"
And Submit the deal
Then Approve the deal from the deal checker
Then Validate in execution report "<TSID>"
Then Create json payload file "<TSID>"
And Create record in ODP "<TSID>"
Examples:
      |TSID|TSID_1|
      |TS61|TS61_1|