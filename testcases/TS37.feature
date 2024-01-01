Feature: TS37_Rule_EnrichParty_UD1

@Regression @TS37 @InitiationRules
Scenario Outline: Rule_EnrichParty_UD1
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then Edit the account and select Participant OBO Responsibility with given "<TSID>".
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS37_1".
Then Add Party Accounts with given "TS37_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Run Rule_EnrichParty_UD1 rule using api with given "<TSID>"
#And Veriy transaction from transaction verifier
#Then Validate transaction in ecommerce report


Examples:
      |TSID   |
      |TS37|
 