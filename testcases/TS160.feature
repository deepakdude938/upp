Feature: TS160_NewDeal_BasicDetails


@Regression @TS160
Scenario Outline: NewDeal_BasicDetails
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Navigate to new deal page to validate that Product field is not defaulted to any values
And Validate that Processing Unit field is not defaulted to any values
Then Create new deal with basic details with given "<TSID>".

Examples:
      |TSID|
      |TS160|