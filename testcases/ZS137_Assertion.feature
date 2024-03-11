Feature: TS137_Payment_with_Retry Failed Scheduled Instruction Assertion

@Assertion @TS137 @Regression @MonWedFri
Scenario Outline: TS137_Payment_with_Retry Failed Scheduled Instruction Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
And Verify in execution Report One Tnx is Rejected and other Rescheduled with given "<TSID>"
Examples:
      |TSID |
      |TS137|