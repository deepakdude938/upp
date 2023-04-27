Feature: TS17

@Regression @TS17
Scenario Outline:  ECommerce Transaction + Party API + Transaction API (REST API calls)
Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create document using data given in  "<TSID>"
And Schedule reminder for required Document with "<TSID>"
Then submit the deal
Then approve the deal from the deal checker common method
And Create workItem for required document schedules
And Document Maker upload document and submit document to checker
And Verify status os document
And Document Checker upload document and submit document
And Verify final status os document 

Examples:
      |TSID   |
      |TS17   |