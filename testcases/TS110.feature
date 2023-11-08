Feature: TS110_Budget_Regression_Using_GB_Account

@Regression @TS110 @ScbRegression
Scenario Outline: TS110_Budget_Regression_Using_GB_Account
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Call the ODP Login Api
Then Create account in Odp with details from excel sheet with given "<TSID>".
Then Call the ODP Logout Api
And Create Account_One From excel sheet with given "<TSID>".
Then Create Budget_Consolidated_Yearly for 1 account with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Add Sub Instruction with payment Instrument TT_UK in Tnx_Maker with given "TS110".
Then Approve the transaction from Transaction Checker with given "<TSID>"
Then logout of the application
Given Open browser and enter url 
Then Login to the application as "txn_checker"
And Check the Transaction staus in execution report with given "<TSID>"
Then Wait for 3 mins
And Check the Utilized budget amount in Audit_Budget Tab with given "<TSID>"
Then Check the Utilized budget amount in Budget_Utilization_Report with given "<TSID>"
And Edit the deal and Check the Utilized budget amount in BudgetTab with given "<TSID>"
And Create payment_BasicDetails in the scheduled Instructions with given "<TSID>"
And Create payment_Schedule in the scheduled Instructions with given "<TSID>"
And Create payment_SubInstruction in the scheduled Instructions with given "<TSID>"
Then submit the deal
Then Create odp json payload file with DealId with given "<TSID>"
And Create record in ODP "<TSID>"
Then approve the deal from the deal checker common method
And Check the Transaction staus in execution report with given "<TSID>"
Examples:
      |TSID|
      |TS110|
