Feature: TS87_API_Attach_PhysicalAccount

@Regression @TS87 @Api 
Scenario Outline: API_Attach_PhysicalAccount
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
Then Add Party Basic Details with given "<TSID>".
Then Submit the deal
Then Approve the deal from the deal checker
Then Call the Attach Physical Account Api for "<TSID>"
Then Edit the deal "<TSID>"
Then Verify Physical account present in Account Tab "<TSID>"
Then Verify Physical Account present in Party "<TSID>"

Examples:
      |TSID|
      |TS87|


