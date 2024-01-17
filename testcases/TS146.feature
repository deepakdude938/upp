Feature: TS146_Retention-Surplus

@Regressio @TS146
Scenario Outline: Create a UPP Deal with Retention-Surplus
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
#And Create Parties in the Parties Tab with given "<TSID>"
Then Create Retention-Surplus in scheduled Instruction with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And check status and instruction type for payment retention with given "<TSID>"

Examples:
      |TSID|
      |TS146|