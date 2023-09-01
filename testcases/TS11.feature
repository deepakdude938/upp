Feature: TS11_Party_Apis_GET_Api_Create_Party_Api_Update_Party_Api

@Regression @TS11 @Api
Scenario Outline: Party_Apis_GET_Api_Create_Party_Api_Update_Party_Api
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Add the Party through Api call with given "TS11-1"
#And Update the Pary Api With given "TS11-3" 
Then call the GET Party Api with given "TS11-2"
And Verify that Party Api got addded in the live deal
Then logout of the application
Examples:
      |TSID   |
      |TS11   |


