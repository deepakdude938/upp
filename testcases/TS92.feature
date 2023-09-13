Feature: TS92_Delete a user by username API

@Api @TS92
Scenario Outline: Delete a user by username API
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Login to UPP through api
Then Call the DELETE_USER_BY_USERNAME_API with given "<TSID>".
And Verify User doesnt exist in UI with given "<TSID>".
And Logout of UPP through api
Then logout of the application
Examples:
      |TSID|
      |TS92|


