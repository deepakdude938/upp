Feature: TS168_Subinstrction_BulkUpload

@Regression @TS168 @MonWedFri
Scenario Outline: TS168_Subinstrction_BulkUpload
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create payment_BasicDetails in the scheduled Instructions with given "<TSID>"
And Create payment_Schedule in the scheduled Instructions with given "<TSID>"
And Create SubInstruction Bulk Upload with "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
Examples:
      |TSID  |
      |TS168 |


