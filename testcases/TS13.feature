Feature: TS13_AdhocTransaction_with_Non_Registered_Beneficiary_Checkbox_Checked

@Regression @TS13 @MonWedFri
Scenario Outline: Create a Transaction in Transaction Maker , with Transaction to Non Registered Beneficiary Checkbox  Checked in Basic Details Page in Deal 
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
#Then logout of the application
#Then Login to the application as "txn_maker"
And Create a Transaction for Non Registered Beneficiary user from Transaction Maker with given "<TSID>"
#Then logout of the application
#Then Login to the application as "txn_checker"
Then Approve the transaction from Transaction Checker with given "<TSID>"
#Then logout of the application
#Then Login to the application as "txn_verifier"
#Then Approve the transaction from Transaction Verifier with given "<TSID>"
And Check the Transaction staus in execution report with given "<TSID>"
#Then logout of the application

Examples:
      |TSID   |
      |TS13   |


