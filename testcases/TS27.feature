Feature: TS27_Bulk_Upload_Adhoc_Transaction


@Regression @TS27
Scenario Outline: Bulk upload transaction
Given Open browser and enter url
 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
#Then Bulk upload the transaction
#Then Logout from Application
#Then Login to the application as "txn_checker"
#Then Approve multiple transactions from Transaction Checker with given "<TSID>"
#Then Logout from Application
#Then Login to the application as "txn_verifier"
#Then Approve multiple transaction from Transaction Verifier with given "<TSID>"
#And Check the Transaction status in execution report with given "<TSID>"
Examples:
      |TSID|
      |TS27|