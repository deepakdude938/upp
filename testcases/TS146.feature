Feature: TS146_Retention-Surplus

@Regression @TS146
Scenario Outline: Create a UPP Deal with Retention-Surplus
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
#And Create Parties in the Parties Tab with given "<TSID>"
Then Create Retention-Surplus in scheduled Instruction with given "<TSID>"
And Create Surplus in the Scheduled Instructions with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Check status and instruction type for Retention_Surplus with given "<TSID>"

Examples:
      |TSID|
      |TS146|