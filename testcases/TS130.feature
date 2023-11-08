#Feature: TS130_Future_Dated_Adhoc_Transaction_ with_GB_Account
#
#@Regression @TS130 @ScbRegression
#Scenario Outline: TS130_Future_Dated_Adhoc_Transaction_ with_GB_Account
#Given Open browser and enter url 
#Then Login to the application as "txn_maker"
#And Create new deal with basic details with given "<TSID>".
#And Call the ODP Login Api
#Then Create account in Odp with details from excel sheet with given "<TSID>".
#Then Call the ODP Logout Api
#And Create Account_One From excel sheet with given "<TSID>".
#Then submit the deal
#Then approve the deal from the deal checker common method
#And Add Sub Instruction with payment Instrument TT_UK in Tnx_Maker with given "<TSID>".
#Then Approve the transaction from Transaction Checker with given "<TSID>"
#Then logout of the application
#Given Open browser and enter url 
#Then Login to the application as "txn_checker"
#And Add Sub Instruction with payment Instrument TT_UK in Tnx_Maker with given "TS130_1".
#Then Approve the transaction from Transaction Checker with given "TS130_1"
#Examples:
      #|TSID  |
      #|TS130 |


