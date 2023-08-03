Feature: TS25_Product Config


@Regression @TS25
Scenario Outline: Product Config
Given Open browser and enter url 
Then Login to the application as "deal_maker"
Then Create product from Configuration for "<TSID>"
Then Approve product
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payments in the Scheduled Instructions on Friday "<TSID>"
And Submit the deal
Then Approve the deal from the deal checker
Examples:
      |TSID|
      |TS25|