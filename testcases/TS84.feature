Feature: TS84_Attach_Detach_Account

@Regression @TS84 @Api
Scenario Outline: API_Attach_Detach_Account
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Create Virtual account in ODP with given "TS84_Virtual".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then submit the deal
Then approve the deal from the deal checker common method


Examples:
      |TSID|
      |TS84|


