Feature: TS08

@Regression @TS08
Scenario Outline:  Add Party through Api and creating a txn
#Given Open browser and enter url 
#Then Login to the application as "txn_maker"
#And Create deal with basic details with given "<TSID>".
#And Create two Accounts with given "<TSID>"
#And Create two eCommerce  Parties in the Parties Tab with given "<TSID>"
And Add the Party using  Api call with given "<TSID>"
#Then Open and Edit the deal
#And Update the Pary using  Api With given "TS11-3" 

#Then submit the deal
#Then approve the deal from the deal checker common method
Examples:
      |TSID   |
      |TS08   |


