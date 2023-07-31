Feature: TS70

@Regression @TS70
Scenario Outline: Budget_Consolidated_Yearly
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then Create Budget_Consolidated_Yearly with given "<TSID>"
And Create Payments in the scheduled Instructions with given "<TSID>"
Then submit the deal
Then Create odp json payload file for TS70 with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Check the Transaction staus in execution report with given "<TSID>"
Examples:
      |TSID|
      |TS70|


