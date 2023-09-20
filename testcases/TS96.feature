Feature: TS96_Fetch_all_roles_API

@Regression @TS96 @Api
Scenario Outline: Fetch_all_roles_API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Login to UPP through api
Then Call the Fetch_all_roles_API
And Verify the first 3 roles present in UI with given "<TSID>".
And Logout of UPP through api
Then logout of the application
Examples:
      |TSID|
      |TS96|


