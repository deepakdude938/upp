Feature: TS14_Rules and Mappers

@Regression @TS14
Scenario Outline: Rules and Mappers
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And call the ecommerce transaction api with given "<TSID>"
Then Verify the status and amount in eComm Executions Report
Then logout of the application
Examples:
      |TSID   |
      |TS14   |


