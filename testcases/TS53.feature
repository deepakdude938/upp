Feature: TS53

 @Regression @TS53
 Scenario Outline:  Create Entitlments
#		Given Open browser and enter url
    #Then Login to the application as "deal_maker"
    #And Create new deal with basic details with given "<TSID>".
#		And Create two Accounts with given "<TSID>"
		And Add entitlements for deal with given "<TSID>"
#		Then submit the deal


Examples:
      |TSID |
      |TS53|