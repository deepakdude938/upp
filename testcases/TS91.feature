Feature: TS91_Update_User_API

@Api @TS91
Scenario Outline: Create_User_API
#Given Open browser and enter url 
And Call the Update User Status Api with given "<TSID>"
#Then login with updated user using API 
#Then Verify user is able to login

Examples:
      |TSID|
      |TS91|