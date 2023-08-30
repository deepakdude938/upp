Feature: TS52_Split_%OfBalance


@Assertion @TS52
Scenario Outline: TS52_Split_%OfBalance
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
And Verify in Execution Report the Original Amount of both Transactions add upto 100% "<TSID>"
Examples:
      |TSID|
      |TS52|