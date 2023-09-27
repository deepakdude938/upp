Feature: TS99_Add_Role_to_a_User_API

@Regression @TS99 @Api
Scenario Outline: Add Role to a User API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Login to UPP through api
Then Call the Add Role to a User API with given "<TSID>".
Then Verify the role got added in UI with given "<TSID>".
And Logout of UPP through api
Then logout of the application
Examples:
      |TSID|
      |TS99|


