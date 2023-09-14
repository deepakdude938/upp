Feature: TS84_Attach_Detach_Account

@Api @TS84 
Scenario Outline: API_Attach_Detach_Account
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
And Create Account_One From excel sheet with given "<TSID>".
Then Create Virtual account in ODP with given "TS84_Virtual".
Then Call the ODP Logout Api
Then Add Party basic_Details with given "<TSID>".
Then Submit the deal
Then approve the deal from the deal checker common method
Then Call the Attach Account Api for "<TSID>"
Then Edit the deal "<TSID>"
Then Verify Virtual account present in Account Tab "<TSID>"
Then Verify Virtual Account present in Party "<TSID>"
Then Submit the deal
Then approve the deal from the deal checker common method
Then Call the Detach Account Api for "TS84_Detach"
Then Edit the deal "<TSID>"
Then Verify Virtual Account is not disable in Party "<TSID>"


Examples:
      |TSID|
      |TS84|


