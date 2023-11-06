Feature: TS118_Verify_Total_of_DebitandPaymentCurrency 

@Regression @TS118
Scenario Outline: Verify Total of Debit and Payment Currency  
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create Parties in the Parties Tab with given "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Add Transaction Maker Basic Details with given "<TSID>"
And Add Payment Currency and  currency Type  with given "<TSID>"
And Add second Payment Currency and  currency Type  with given "<TSID>"
And Add Payment Currency and  currency Type as Debit currency with given "TS118_1"
And Add Payment Currency and  currency Type as Debit currency with given "TS118_1"
And Verify total debit currency and payment currency

Examples:
      |TSID   |
      |TS118   |

	
