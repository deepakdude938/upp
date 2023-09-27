Feature: TS100_Create Tx OBO both and Ultimate Debtor

@Regression @TS100 @Api
Scenario Outline: Create Tx OBO both and Ultimate Debtor
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS100_1".
Then Add Party Accounts with given "TS100_1".
Then submit the deal
Then approve the deal from the deal checker common method
Then Call the Create Tx OBO both and Ultimate Debtor using api with given "<TSID>"

Examples:
      |TSID   |
      |TS100|