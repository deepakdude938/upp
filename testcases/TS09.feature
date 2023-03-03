Feature: TS09

@Regression @TS09
Scenario Outline: Creating a new Party under Parties tab and onboarding the party to the deal and creating a txn 
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
Then Create party from party maker with given "<TSID>"
And Approve party from party checker with given "<TSID>"
Then Edit the Live deal and add Existing Party with given "<TSID>"
And Add a Transaction using Payment with the updated deal with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
Then logout of the application
Examples:
      |TSID   |
      |TS09   |


