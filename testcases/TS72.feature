#Feature: TS72
#
#@Regression @TS72
#Scenario Outline: Budget_PurposeandDestination_DateRange
#Given Open browser and enter url 
#Then Login to the application as "txn_maker"
#And Create new deal with basic details with given "<TSID>".
#And Create two Accounts with given "<TSID>"
#And Create Parties in the Parties Tab with given "<TSID>"
#Then Create Budget_PurposeandDestination_DateRange with given "<TSID>"
#And Create Payment_BasicDetails in the scheduled Instructions with given "<TSID>"
#And Create Payment_Schedule in the scheduled Instructions with given "<TSID>"
#And Create Payment_SubInstruction in the scheduled Instructions with given "<TSID>"
#And Create Payment_Retry in the scheduled Instructions with given "<TSID>"
#And Create Payment_Notification in the scheduled Instructions with given "<TSID>"
#Then submit the deal
#Then Create odp json payload file for Budget with given "<TSID>"
#And Create record in ODP "<TSID>"
#Then approve the deal from the deal checker common method
#And Check the Transaction staus in execution report with given "<TSID>"
#Examples:
      #|TSID|
      #|TS72|


