Feature: TS16

@Regression @TS16
Scenario Outline: Create a XCRO Deal with Payment Retention
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then Create Payment_Retention in scheduled Instruction with given "<TSID>"
Then submit the deal
#Then logout of the application
#Then Login to the application as "txn_checker"
Then approve the deal from the deal checker common method
And check status and instruction type for payment retention with given "<TSID>"
#Then logout of the application
Examples:
      |TSID|
      |TS16|


