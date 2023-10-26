Feature: TS119_Rule_Account_Key

@Regression @TS119 @InitiationRules
Scenario Outline: TS119_Rule_Account_Key
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Onboard the Account One and add the AccountKey with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "TS119_1".
Then Call the ODP Logout Api
And Onboard the Account Two and add the AccountKey with given "TS119_1".
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS119_1".
Then Add Party Accounts with given "TS119_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Login to UPP through api
And Call the Rule_Account_Key Api with given "<TSID>".
Then Verify the SourceAccountNumber in Ecomm Executions Report with given "<TSID>".
And Logout of UPP through api
Examples:
      |TSID  |
      |TS119 |


