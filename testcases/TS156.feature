Feature: TS156_Login_Invalid_Credentials


@Regression @TS156
Scenario Outline: Login_Invalid_Credentials
Given Open browser and enter url 
Then Login to the application as "invalid_deal_maker"
And Verify that the user is not able to login

Examples:
      |TSID|
      |TS156|