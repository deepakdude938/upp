Feature: TS88_Api_CreateTransaction

@Regression @TS88 @Api
Scenario Outline: Api_CreateTransaction
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "<PartiesID>"
Then submit the deal
Then approve the deal from the deal checker common method
Then Call the Create Transaction Api for "<TSID>"
Then Validate in execution report for scheduled record using endToEndId "<TSID>"
Then Call the Rollback Transaction Api "<TSID>"
Then Validate in execution report record should not be there

Examples:
      |TSID   |PartiesID|
      |TS88|TS88_1|