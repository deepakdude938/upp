Feature: TS111_Api_UpdateTransactionstatus

@Regression @TS111 @Api
Scenario Outline: Api_UpdateTransactionstatus
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "<PartiesID>"
Then Call the Create Transaction Api for TS111 "<TSID>"
#Then Call the update transaction status as hold for "<PartiesID>"
#Then Verify transaction hold status in ecomm report "<TSID>"



Examples:
      |TSID   |PartiesID|
      |TS111|TS111_1|