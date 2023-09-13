Feature: TS85_Party_Apis_Delete_Party_API

@Regression @TS85 @Api
Scenario Outline: Party_Apis_Delete_Party_API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Add the Party through Api call with given "TS85-1"
Then Call the Delete Party API with given "TS85-2"
And Edit the Deal and Verify in UI no Parties are added
And Logout of UPP through api
Then logout of the application
Examples:
      |TSID|
      |TS85|


