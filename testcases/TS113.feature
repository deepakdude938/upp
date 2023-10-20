#Feature: TS113_Multiple_Transactions_Regression_Using_GB_Account
#
#@Regression @TS113
#Scenario Outline: TS113_Multiple_Transactions_Regression_Using_GB_Account
#Given Open browser and enter url 
#Then Login to the application as "txn_maker"
#And Create new deal with basic details with given "<TSID>".
#And Call the ODP Login Api
#Then Create account in Odp with details from excel sheet with given "<TSID>".
#Then Call the ODP Logout Api
#And Create Account_One From excel sheet with given "<TSID>".
#Then Create Budget_Consolidated_Yearly for 1 account with given "<TSID>"
#And Create payment_BasicDetails in the scheduled Instructions with given "<TSID>"
#And Create payment_Schedule in the scheduled Instructions with given "<TSID>"
#And Create payment_SubInstruction in the scheduled Instructions with given "<TSID>"
#Then Create linked Instruction Payment with given  "<TSID>".
#Then submit the deal
#Then approve the deal from the deal checker common method
#Then submit the deal to transaction checker
#Then submit the deal to transaction verifier
#Examples:
      #|TSID|
      #|TS113|
