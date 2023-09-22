Feature: TS97_Create Tx(tx with two fragments)


@Regression @TS97 @InitiationRules
Scenario Outline: Create Tx(tx with two fragments)
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two eCommerce  Parties in the Parties Tab with given "<PartiesID>" and "<TSID>"

Examples:
     |TSID|PartiesID|
       |TS97|TS97_Participant1|