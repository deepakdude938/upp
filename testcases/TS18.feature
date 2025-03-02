Feature: TS18_PriorityDependency_SameDay

@Regression @TS18 @MonWedFri
Scenario Outline: Create a UPP Deal with 2 Payments where 1 payment is dependent on other Payment
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payments in the scheduled Instructions with given "<TSID>"
Then Click On Accounts Tab
And Create Payments in the scheduled Instructions with given "TS18-B"
And Add Priority dependency with Same Day Dependeny
Then submit the deal
#Then logout of the application
#Then Login to the application as "txn_checker"
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Check Both Transactions Status is Scheduled "<TSID>"
#Then logout of the application
Examples:
      |TSID|
      |TS18|


