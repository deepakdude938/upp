Feature: TS104_Account_Ammendment_UI

@Api @TS104
Scenario Outline: Account_Ammendment_UI
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS104_1".

Examples:
      |TSID   |
      |TS104|