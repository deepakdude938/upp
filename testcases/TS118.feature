Feature: TS118_Verify_Total_of_DebitandPaymentCurrency 

@Regression @TS118 @MonWedFri
Scenario Outline: Verify Total of Debit and Payment Currency  
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "<PartiesID>"
Then Add Debit and Payment currency with given "<TSID>" 
#And Add Transaction Maker Basic Details with given "<TSID>"
#And Add Payment Currency and  currency Type  with given "<TSID>"
And Add second Payment Currency and  currency Type  with given "<TSID>"
And Add Payment Currency and  currency Type as Debit currency with given "TS118_1"
And Add Payment Currency and  currency Type as Debit currency with given "TS118_1"
And Verify total debit currency and payment currency

Examples:
     |TSID   ||PartiesID|
       |TS118   ||TS118_Participant1|

	
