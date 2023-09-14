Feature: TS93_Update_UserStatus_API

@Api @TS93
Scenario Outline: Update_UserStatus_API
Given Open browser and enter url 
And Call the Update User Attribute Api with given "<TSID>"
#Then login with updated user using "<TSID>"
#Then Verify user is not  able to login


Examples:
      |TSID|
      |TS93|