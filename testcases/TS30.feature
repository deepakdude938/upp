Feature: TS30 - Party_EnrichDebtor


@Regression @TS30 @rules
Scenario Outline: Party_EnrichDebtor
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two eCommerce  Parties in the Parties Tab with given "<TSID>" and "<PartiesID>"
And Update json for Party_EnrichDebtor rule api "<TSID>"
And Login to UPP through api
And Call Party_EnrichDebtor rule api
Then Verify transaction should be present at Ecomm Transaction Verifier que
Examples:
     |TSID|PartiesID|
       |TS30|TS30_Participant1|