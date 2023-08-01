Feature: TS69_Budget_Destination_Quarterly_Assertion


@Assertion @TS69
Scenario Outline: Budget_Destination_Quarterly_Assertion

Given Open browser and enter url 
Then Login to the application as "deal_maker"
Then Fetch record from ODP with "<TSID>"
Then Validate status in execution report "<TSID>"

Examples:
      |TSID|
      |TS69|