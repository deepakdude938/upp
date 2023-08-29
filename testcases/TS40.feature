Feature: TS40_Rule_dealRefId_V3_UD1

@Regression @TS40 @InitiationRules
Scenario Outline: Rule_dealRefId_V3_UD1
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS40_1".
Then Add Party Accounts with given "TS40_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Run Rule_dealRefId_V3_UD1 using api with given "<TSID>"

Examples:
      |TSID   |
      |TS40|