Feature: TS149_Reschedule_transaction


@Regression @TS149
Scenario Outline: Reschedule_transaction
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create Holiday "<TSID>"
And Approve Holiday
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payments in schedule instruction for "<TSID>"
And Create Payment_SubInstruction for "<TSID>"
Then Create Retry Payment with given "<TSID>"
And Create Payment_Notification in the scheduled Instructions with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then Approve the deal from the deal checker
And Check the Transaction staus in execution report with given "<TSID>"
Examples:
      |TSID|
      |TS149|