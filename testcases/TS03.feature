Feature: TS03_Payment_Scheduled_Instruction

@Regression @TS03
Scenario Outline: Create a UPP Deal with Payments
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payments in the scheduled Instructions with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
#Then logout of the application
#Then Login to the application as "txn_checker"
Then approve the deal from the deal checker common method
#Then logout of the application
And Check the Transaction staus in execution report with given "<TSID>"
Examples:
      |TSID   |
      |TS03   |


