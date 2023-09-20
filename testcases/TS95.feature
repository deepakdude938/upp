Feature: TS95_Get_a_Userlist_of_TenUser_API

@Regression @TS95 @Api
Scenario Outline: Get_a_Userlist_of_TenUser_API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Login to UPP through api
Then Call the Get_List_of_TenUsers API
And Logout of UPP through api
Then logout of the application
Examples:
      |TSID|
      |TS95|