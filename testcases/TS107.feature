Feature: TS107_Account_Amendment_With_Transaction_UI

@Api @TS107
Scenario Outline: Account_Amendment_With_Transaction_UI
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS107_1".
Then Verify Account is updated

Examples:
      |TSID   |
      |TS107|