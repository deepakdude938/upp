Feature: TS66

 @InstructedControlAmount @TS66  
Scenario Outline: Instructed Control Amount
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS66_1".
Then Add Party Accounts with given "TS66_1".
Then Submit the deal
Then Approve the deal from the deal checker
And Run Instructed Control Amount using api with given "<TSID>"

Examples:
      |TSID   |
      |TS66|