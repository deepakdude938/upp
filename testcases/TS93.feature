Feature: TS93_Update_UserAttribute_API

@Regression @TS93 @Api
Scenario Outline: Update_UserAttribute_API
Given Open browser and enter url 
And Call the Update User Attribute Api with given "<TSID>"
#Then login with updated user using update API 



Examples:
      |TSID|
      |TS93|