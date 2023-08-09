Feature: TS28_Bulk Upload Ecomm Transaction


@Regression @TS28
Scenario Outline: Bulk Upload Ecomm Transaction
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "<PartiesID>"
And Update excel file according to data
Then Bulk upload Ecomm Transaction
Then Approve ecomm transaction from Ecommerce Txn Checker
#Then Approve ecomm transaction from Ecommerce Txn Verifier
Then Validate ecomm transaction in Reports
Examples:
      |TSID|PartiesID|
      |TS28|TS28_Participant1|