Feature: TS129_Verify_Name_AddressLine_DealRefID_ParticipantRefID_fields_Available

@Regression @TS129
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
And Verify Name and Address line, deal refid, participant ref id  gets displayed with given "<TSID>"


Examples:
      |TSID   ||PartiesID|
       |TS129   ||TS129_Participant1|