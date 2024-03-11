Feature: TS139_Debtor_Currency_Option

@Regression @TS139 @MonWedFri
Scenario Outline:  Debtor Currency Option
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
And Verify Debtor Currency Option available with given "<TSID>"

Examples:
      |TSID   ||PartiesID|
       |TS139   ||TS139_Participant1|