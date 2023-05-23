Feature: TS31

@Regression @TS31
Scenario Outline: Rule_Non_OBO
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account From excel sheet with given "<TSID>".
Examples:
      |TSID   |
      |TS31   |


