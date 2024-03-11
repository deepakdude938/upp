Feature: TS97_Create Tx(tx with two fragments)


@Regression @TS97 @Api @MonWedFri
Scenario Outline: Create Tx(tx with two fragments)
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Hit Back button
And Create two eCommerce  Parties in the Parties Tab with given "<PartiesID>" and "TS97_2"
Then Call the Create tx with two fragments for "<TSID>"

Examples:
     |TSID|PartiesID|
       |TS97|TS97_1|