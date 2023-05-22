Feature: TS29

@Regression @TS29
Scenario Outline: Update the Details in Party Tab and check whether changes visible in Audit
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
Then Edit the Live deal and add add the Party Tab with given "TS29_1"
Then submit the deal
Then approve the deal from the deal checker common method
And Verfiy In Audit Tab that update log is Updated with new party
Then Edit the Live deal and add Budget
And Create a Budget with given "<TSID>"
And Create Payment with budget with given "<TSID>"
Then submit and approve the deal
Then approve the deal from the deal checker common method
Then Verify In Audit Budget Tab whether Budget details are Correct with given "<TSID>"
Then logout of the application

Examples:
      |TSID   |
      |TS29   |


