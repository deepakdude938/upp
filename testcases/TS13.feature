Feature: TS13

@Regression @TS13
Scenario Outline: Create a Transaction in Transaction Maker , with Transaction to Non Registered Beneficiary Checkbox  Checked in Basic Details Page in Deal 
Given Open browser and enter url 
#userType can be deal_maker, deal_cherk, txn_maker, txn_checker, txn_verifier
Then Login to the application as "deal_maker"
#And Create deal with basic details with given "<TSID>".
#And Create two Accounts with given "<TSID>"
#And Create Parties in the Parties Tab with given "<TSID>"
#Then submit the deal
#Then approve the deal from the deal checker common method
#Then logout of the application
#Then Login to the application as "txn_maker"
And Create a Transaction for Non Registered Beneficiary user from Transaction Maker with given "<TSID>"

Examples:
      |TSID   |
      |TS13   |


