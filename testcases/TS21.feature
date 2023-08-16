Feature: TS21_BalanceReporting

@Regression @TS21
Scenario Outline: Create a UPP deal with Balance Reporting from Scheduled Instructions Tab
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then Create Balance Reporting Tnx from Scheduled Instruction Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Check Status and Instruction name as Balance Report with given "<TSID>"
#Then logout of the application
Examples:
      |TSID|
      |TS21|


