Feature: TS86_Create_User_API

@Api @TS86 
Scenario Outline: Create_User_API
Given Open browser and enter url 
And Call the Create User Api with given "<TSID>"
Then login with updated user using API 
Then Verify user is able to login

Examples:
      |TSID|
      |TS86|