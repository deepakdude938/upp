Feature: TS61_Assertion


@Regression @TS61 @Assertion
Scenario Outline: Payment Retention Decimal Assertion
Given Open browser and enter url 
Then Login to the application as "deal_maker"
Then Fetch record from ODP with "<TSID>"
Then Validate assertion in execution report "<TSID>"
Examples:
      |TSID|
      |TS61|