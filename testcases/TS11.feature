Feature: TS11

@Regression @TS11
Scenario Outline:  Add Party through Api and creating a txn
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Add the Party through Api call
#And Add a Transaction using Payment with the updated deal with given "<TSID>"
#Then submit the deal
#Then approve the deal from the deal checker common method
#Then logout of the application
Examples:
      |TSID   |
      |TS11   |


