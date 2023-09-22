Feature: TS98_Fetch a Role API

@Regression @TS98 @Api
Scenario Outline: Fetch_all_roles_API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Login to UPP through api
Then Call the Fetch_Role_API
And Logout of UPP through api
Then logout of the application
Examples:
      |TSID|
      |TS98|


