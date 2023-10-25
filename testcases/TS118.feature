Feature: TS118_Verify

@Regression @TS118
Scenario Outline: Create a Transaction in Transaction Maker , with Transaction to Non Registered Beneficiary Checkbox  Checked in Basic Details Page in Deal 
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Add Transaction Maker Basic Details with given "<TSID>"
And Add Payment Currency and  currency Type  with given "<TSID>"
#Then logout of the application
#Then Login to the application as "txn_checker"
#Then Approve the transaction from Transaction Checker with given "<TSID>"
#Then logout of the application
#Then Login to the application as "txn_verifier"
#Then Approve the transaction from Transaction Verifier with given "<TSID>"
#And Check the Transaction staus in execution report with given "<TSID>"
#Then logout of the application

Examples:
      |TSID   |
      |TS118   |

	
