Feature: TS07


@Regression @TS07
Scenario Outline: ECommerce Transaction (Deal with eComm Party, Ecomm Transaction Maker, Checker and Approver)
Given Open browser and enter url 
 #userType can be deal_maker, deal_cherk, txn_maker, txn_checker, txn_verifier
Then Login to the application as "deal_maker"
#And Create new deal with basic details with given "<TcId>".
#And Create two Accounts with given "<TcId>"
#And Create two eCommerce  Parties in the Parties Tab with given "<TcId>"
And Add deal in ecommerce transaction maker queue "<TcId>"
And Submit the deal to ecommerce transaction checker
Examples:
      |TcId|
      |TS07|