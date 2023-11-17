Feature: TS125_Verify_DealRefID_ParticipantRefID_field_Available

@Regression @TS125
Scenario Outline: Verify DealRefID ParticipantRefID field Available
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
And Verify Deal refId and Participant Refid gets displayed with given "<TSID>"


Examples:
      |TSID   ||PartiesID|
       |TS125   ||TS125_Participant1|