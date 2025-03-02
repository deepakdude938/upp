Feature: TS79_Verify_Trasaction_Amount_Greater_Than_Limit


@Regression @TS79 @Daily
Scenario Outline: Verify Trasaction amount greater than limit
Given Open browser and enter url 
Then Login to the application as "deal_maker"
Then Create product from Configuration with transaction limit for "<TSID>"
Then Approve product
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
#Then logout of the application
#Then Login to the application as "txn_maker"
And Create a Transaction from Transaction Maker with given "<TSID>"
#Then logout of the application
#Then Login to the application as "txn_checker"
Then Approve the transaction from Transaction Checker with given "<TSID>" for transaction amount greater than limit
Then Approve the transaction from Transaction Verifier for transaction limit with given "<TSID>"
And Check the Transaction staus in execution report with given "<TSID>"

Examples:
      |TSID|
      |TS79| 	