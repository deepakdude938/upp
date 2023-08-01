Feature: TS01_Deal_TwoAccounts


@Regression @TS01
Scenario Outline: Create deal with two accounts
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TcId>".
And Create two Accounts with given "<TcId>"
Examples:
      |TcId|
      |TS01|
 