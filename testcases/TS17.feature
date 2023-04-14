Feature: TS17

@Regression @TS17
Scenario Outline:  ECommerce Transaction + Party API + Transaction API (REST API calls)
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create document using data given in  "<TSID>"

Examples:
      |TSID   |
      |TS17   |