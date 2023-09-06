Feature: TS80_Party_Apis_Create_Party_API(Company)_Update_Party_API

@Regression @TS80 @Api
Scenario Outline: Party_Apis_Create_Party_API(Company)_Update_Party_API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Add the Party through Api call with given "TS80-1"
And Update the Pary Api With given "TS80-2" 
Then Verify in UI the changes made by Update Party API 
And Logout of UPP through api
Then logout of the application
Examples:
      |TSID|
      |TS80|


