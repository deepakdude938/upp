Feature: TS121_Verify_Error_Message

@Regression @TS121
Scenario Outline: Verify error message  
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then Edit the account and select OBO Responsibility with given "<TSID>".
#And Add two ecomm accounts with given "<TSID>" and "<PartiesID>"
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then Edit the second account and select OBO Responsibility with given "<TSID>".
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "<PartiesID>"
And Add deal in ecommerce transaction maker queue with ultimateDebtor "<TSID>"


Examples:
      |TSID   ||PartiesID|
       |TS121   ||TS121_Participant1|