Feature: TS112_Api_UpdateTransactionstatusCancel

@Regression @TS112 @Api
Scenario Outline: Api_UpdateTransactionstatus_Cancel
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "<PartiesID>"
Then Call the Create Transaction Api for TS112 "<TSID>"
Then Call the update transaction status as Cancel for "<PartiesID>"
Then Verify transaction Cancel status in ecomm report "<TSID>"



Examples:
      |TSID   |PartiesID|
      |TS112|TS112_1|