Feature: TS141_Payment_with_Retry Failed Scheduled Instruction with Retry_Type Custom Assertion

@Assertion @TS141 @Regression @TuesThurs
Scenario Outline: TS141_Payment_with_Retry Failed Scheduled Instruction with Retry_Type Custom Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
And Verify in execution Report One Tnx is Rejected and other Rescheduled with given "<TSID>"
Examples:
      |TSID |
      |TS141|