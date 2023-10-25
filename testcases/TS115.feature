Feature: TS115_Api_UpdateTransactionstatusSchedule

@Regression @TS115 @Api
Scenario Outline: Api_UpdateTransactionstatus_Schedule
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "<PartiesID>"
Then Call the Create Transaction Api for TS113 "<TSID>"
Then Call the update transaction status as Schedule for "<PartiesID>"
Then Verify transaction Schedule status in ecomm report "<TSID>"



Examples:
      |TSID   |PartiesID|
      |TS115|TS115_1|