Feature: TS03

@Regression @TS03
Scenario Outline: Create a XCRO Deal with Payments
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payments in the scheduled Instructions with given "<TSID>"
Then submit the deal
#Then logout of the application
#Then Login to the application as "txn_checker"
Then approve the deal from the deal checker common method
Then logout of the application
Examples:
      |TSID   |
      |TS03   |


