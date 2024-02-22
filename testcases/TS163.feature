Feature: TS163_Retention_%Balance

@Regression @TS163
Scenario Outline: Create a UPP Deal Retention
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Retention in the Scheduled Instructions with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Check status and instruction type for retention with given "<TSID>"
Examples:
      |TSID|
      |TS163|


