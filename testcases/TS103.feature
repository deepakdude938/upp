Feature: TS103_Adhoc Transaction Regression with GB Account

@Regression @TS103
Scenario Outline: TS103_Adhoc Transaction Regression with GB Account
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Add First Sub Intrsuction with payment Instrument BT_UK with given "TS103_1".
And Add Second Sub Intrsuction with payment Instrument LT_UK with given "TS103_2".
And Add Third Sub Intrsuction with payment Instrument TT_UK with given "TS103_3".
Then Approve the transaction from Transaction Checker with given "TS103_3"
Then logout of the application
Given Open browser and enter url 
Then Login to the application as "txn_checker"
And Check the Transaction staus of all 3 Transactions in execution report with given "<TSID>"
Examples:
      |TSID  |
      |TS103 |


