Feature: TS08_EcommerceTransaction_With_APIS

@Regression @TS08
Scenario Outline:  ECommerce Transaction + Party API + Transaction API (REST API calls)
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "TS08_02"
#Then submit the deal
#Then approve the deal from the deal checker common method
And Add the Party using  Api call with given "TS08_01"
And Update the Pary Api using given "TS11-3" 
Then Edit the live deal
Then submit the deal
Then approve the deal from the deal checker common method
And  Add the transaction using  Api call with given "<TSID>" and "TS08_01" and "TS08_02"
Then Verify the Transaction status in eComm Executions Report

Examples:
      |TSID   |
      |TS08   |


