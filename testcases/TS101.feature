Feature: TS101_Rule_Without_PaymentInfoDetails

@Regression @TS101 @InitiationRules @TuesThurs
Scenario Outline: TS101_Rule_Without_PaymentInfoDetails
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then Add Party basic_Details with given "<TSID>".
Then Click On Accounts Tab
Then Add Party basic_Details with given "TS101_1".
Then Add Party Accounts with given "TS101_1".
Then submit the deal
Then approve the deal from the deal checker common method
And Login to UPP through api
And Call the Rule_Without_PaymentInfoDetails Api with given "<TSID>".
Then Create odp json payload file with EndToEndId with given "<TSID>"
And Create record in ODP "<TSID>"
Then Verify the SourceAccountNumber in Ecomm Executions Report with given "<TSID>".
And Logout of UPP through api
Examples:
      |TSID  |
      |TS101 |


