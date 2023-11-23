Feature: TS132_Verify_Name_feild_Is_Mandatory

@Regression @TS132
Scenario Outline: Verify Name Address line deal ref id, participant ref id field Available
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
And Verify Name as mandatory with given "<TSID>"


Examples:
      |TSID   ||PartiesID|
       |TS132   ||TS132_Participant1|