Feature: TS18_PriorityDependency_SameDay_Assertion


@Assertion @TS18 @Regression  @MonWedFri
Scenario Outline: PriorityDependency_SameDay_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
And Check the Execution Report that both Tnx are rejected and verify the Error Message with given "<TSID>"
Examples:
      |TSID|
      |TS18|