Feature: TS91_Update_UserStatus_API

@Regression @TS91 @Api
Scenario Outline: Update_UserStatus_API
Given Open browser and enter url 
And Call the Update User Status Api with given "<TSID>"
Then login with updated user using update API 
Then Verify user is not  able to login


Examples:
      |TSID|
      |TS91|