Feature: TS140_Payment_with_Retry Failed Scheduled Instruction Till Next Date

@Assertion @TS140 @Regression @TuesThurs
Scenario Outline: TS140_Payment_with_Retry Failed Scheduled Instruction Till Next Date
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
And Verify in execution Report One Tnx is Rejected and other Rescheduled and Reschudled date with given "<TSID>"
Examples:
      |TSID |
      |TS140|