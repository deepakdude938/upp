Feature: TS23

@Regression @TS23
Scenario Outline: Deal Lifecycle Maker and Checker
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payments in the scheduled Instructions with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Close the deal from the Live Deal Section with given "<TSID>"
Then Create Lifecycle maker workitem with given "<TSID>"
And Approve the Workitem from Dealchecker with given "<TSID>"
Then Verify the deal Status as Closed
#Then logout of the application
Examples:
      |TSID|
      |TS23|


