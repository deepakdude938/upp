Feature: TS148_Account_Onboard_Offboard

@Regression @TS148
Scenario Outline: Account Onboard Offboard
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Offboard one of the accounts with given "<TSID>"
Then submit the deal
Then Approve the deal from the deal checker
Then Check in Live deals for the newly created deal with given "<TSID>"
#Then logout of the application
Examples:
      |TSID|
      |TS148|