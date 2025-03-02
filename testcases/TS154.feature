Feature: TS154_ProdIssue5

@DupTxnProdIssue @TS154 @Biweekly
Scenario Outline: ProdIssue5
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Payments scheduled Instructions with given "<TSID>"
And Create payment_Schedule in the scheduled Instructions with given "<TSID>"
And Create payment_SubInstruction in the scheduled Instructions with given "<TSID>"
And Submit the deal
Then Approve the deal from the deal checker
Then Validate all record status and count the record "<TSID>"
Then Validate cron 
Then Validate extra record not created "<TSID>"
Examples:
      |TSID|
      |TS154|