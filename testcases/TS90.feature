Feature: TS90_Get_a_User_by_username_API

@Regression @TS90 @Api
Scenario Outline: Get a User by username API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Login to UPP through api
Then Call the GET_USER_BY_USERNAME_API
And Verify User Details in the UI  with given "<TSID>".
And Logout of UPP through api
Then logout of the application
Examples:
      |TSID|
      |TS90|


