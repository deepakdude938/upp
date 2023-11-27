Feature: TS134_Ultimate_Debtor_Checkbox_should_be_disabled

@Regression @TS134
Scenario Outline:  Ultimate Debtor checkbox should be disabled
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
And Verify ultimate debetor checkbox is disable with given "<TSID>"

Examples:
      |TSID   ||PartiesID|
       |TS134   ||TS134_Participant1|