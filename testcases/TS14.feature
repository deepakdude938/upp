Feature: TS14

@hold @TS14
Scenario Outline: Txn creation+ Rules + Mappers
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And call the ecommerce transaction api with given "<TSID>"
Then Verify the EcommTnx in eComm Executions Report
Then logout of the application
Examples:
      |TSID   |
      |TS14   |


