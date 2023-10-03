Feature: TS102_Account_Ammendment

@TS102 @Api
Scenario Outline: Account_Ammendment
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".

Examples:
      |TSID   |
      |TS102|