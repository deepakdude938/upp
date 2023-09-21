Feature: TS94_Get list of Users API

@Regression @TS94 @Api
Scenario Outline: Get list of Users API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Login to UPP through api
Then Call the Get_List_of_Users API
And Verify 3 User Details in UI with given "<TSID>".
And Logout of UPP through api
Then logout of the application
Examples:
      |TSID|
      |TS94|


