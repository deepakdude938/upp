Feature: TS64_Rule_OBO_Participant_Enrich1

@Regression @TS64 @InitiationRules @TuesThurs
Scenario Outline: Rule_OBO_Participant_Enrich1
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then Edit the account and select OBO Responsibility with given "TS64_1".
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS64_1".
Then Add Party Accounts with given "TS64_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Login to UPP through api
And Call the Rule_OBO_Participant_Enrich1 with given "<TSID>".
Then Verify in EcommExecution the status
And Logout of UPP through api
Examples:
      |TSID   |
      |TS64   |


