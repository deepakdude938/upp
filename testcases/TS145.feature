Feature: TS145_Deal_Lifecycle_Close_and_Checker_Rejects

@Regression @TS145
Scenario Outline: Deal Lifecycle Checker Rejects Close Request
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Close the deal from the Live Deal Section with given "<TSID>"
Then Create Lifecycle maker workitem with given "<TSID>"
And Reject the Workitem from Dealchecker with given "<TSID>"
Then Deal Lifecycle Maker should have the rejected deal
#Then logout of the application
Examples:
      |TSID|
      |TS145|


