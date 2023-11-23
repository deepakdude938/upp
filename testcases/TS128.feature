Feature: TS128_Verify_Name_AddressLine_field_Available

@Regression @TS128
Scenario Outline: Verify Name Address line field Available
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "<PartiesID>"
And Verify Name and Address line gets displayed with given "<TSID>"


Examples:
      |TSID   ||PartiesID|
       |TS128   ||TS128_Participant1|