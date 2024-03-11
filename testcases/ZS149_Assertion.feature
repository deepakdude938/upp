Feature: TS149_Payment_with_Retry Failed Scheduled Instruction with Retry_Type SameDay Assertion

@Assertion @TS149 @Regression @TuesThurs
Scenario Outline: TS149_Payment_with_Retry Failed Scheduled Instruction with Retry_Type SameDay Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
And Verify in execution Report One Tnx is Rejected and Reschudled date with same day "<TSID>"
Examples:
      |TSID |
      |TS149|