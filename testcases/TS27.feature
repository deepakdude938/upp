Feature: TS27 Bulk upload transaction


@Regression @TS27
Scenario Outline: Bulk upload transaction
#Given Open browser and enter url 
#Then Login to the application as "deal_maker"
#And Create new deal with basic details with given "<TSID>".
#And Create two Accounts with given "<TSID>"
#And Create Parties in the Parties Tab with given "<TSID>"
#Then submit the deal
#Then approve the deal from the deal checker common method
Then Bulk upload the transaction

Examples:
      |TSID|
      |TS27|