Feature: TS116_Rule_With_Partial_PaymentInfoDetails

@Regression @TS116 @InitiationRules
Scenario Outline: TS116_Rule_With_Partial_PaymentInfoDetails
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS116_1".
Then Add Party Accounts with given "TS116_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Login to UPP through api
And Call the Rule_With_Partial_PaymentInfoDetails Api with given "<TSID>".
Then logout of the application
Given Open browser and enter url 
Then Login to the application as "txn_checker"
Then Verify the SourceAccountNumber in Ecomm Executions Report with given "<TSID>".
And Logout of UPP through api
Examples:
      |TSID  |
      |TS116 |


