Feature: TS52

@Regression @TS52
Scenario Outline: Create a UPP Deal with Sheduled Instruction with Split_%OfBalance
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
And Create Payment_BasicDetails in the scheduled Instructions with given "<TSID>"
And Create Payment_Schedule in the scheduled Instructions with given "<TSID>"
And Create Payment_SubInstruction in the scheduled Instructions with given "<TSID>"
And Create Payment_SubInstruction in the scheduled Instructions with given "TS52_1"
And Create Payment_Retry in the scheduled Instructions with given "<TSID>"
And Create Payment_Notification in the scheduled Instructions with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Verify in Execution Report the Original Amount and Transfer info as percentage with given "<TSID>"
Examples:
      |TSID|
      |TS52|


