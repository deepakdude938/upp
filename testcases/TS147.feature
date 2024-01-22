Feature: TS147_Deal_With_Basic_Details_Entered

@Regression @TS147
Scenario Outline: Deal With Basic Details Entered
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then submit the deal
Then Approve the deal from the deal checker
Then Check in Live deals for the newly created deal with given "<TSID>"
#Then logout of the application
Examples:
      |TSID|
      |TS147|