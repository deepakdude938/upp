Feature: TS18_Assertion PriorityDependency_SameDay_Assertion


@Assertion @TS18
Scenario Outline: PriorityDependency_SameDay_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Examples:
      |TSID|
      |TS18|