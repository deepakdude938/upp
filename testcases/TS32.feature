Feature: TS32

@Regression @TS32 @InitiationRules
Scenario Outline: Rule_Static_OBO
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS32_1".
Then Add Party Accounts with given "TS32_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Run static obo rule using api with given "<TSID>"


Examples:
      |TSID   |
      |TS32|


