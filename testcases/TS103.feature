#Feature: TS103_Adhoc Transaction Regression with GB Account
#
#@Regression @TS103
#Scenario Outline: TS103_Adhoc Transaction Regression with GB Account
#Given Open browser and enter url 
#Then Login to the application as "txn_maker"
#And Create new deal with basic details with given "<TSID>".
#And Call the ODP Login Api
#Then Create account in Odp with details from excel sheet with given "<TSID>".
#Then Call the ODP Logout Api
#And Create Account_One From excel sheet with given "<TSID>".
#Then submit the deal
#Then approve the deal from the deal checker common method
#Examples:
      #|TSID  |
      #|TS103 |


