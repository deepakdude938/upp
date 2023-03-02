Feature: TS09

@Regression @TS09
Scenario Outline: Traditional XCRO Transaction (Maker, Checker, Verifier) 
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker for TS06
#Then Create party from party maker with given "<TSID>"
#And Approve party from party checker with given "<TSID>"
Then Edit the Live deal and add Existing Party with given "<TSID>"
Then logout of the application
Examples:
      |TSID   |
      |TS09   |


