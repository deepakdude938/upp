Feature: TS61_Payment Retention Decimal Assertion


@Assertion @TS61 @Regression  @MonWedFri
Scenario Outline: Payment Retention Decimal Assertion
Given Open browser and enter url 
Then Login to the application as "deal_maker"
Then Fetch record from ODP with "<TSID>"
Then Validate assertion in execution report "<TSID>"
Examples:
      |TSID|
      |TS61|