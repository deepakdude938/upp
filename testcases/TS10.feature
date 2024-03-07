Feature: TS10_HolidayAction


@Regression @TS10 @TuesThurs
Scenario Outline: Holiday combination
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create Holiday "<TSID>"
And Approve Holiday
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payments in the Scheduled Instructions on Friday "<TSID>"
Then Check schedule payment next business day
Examples:
      |TSID|
      |TS10|