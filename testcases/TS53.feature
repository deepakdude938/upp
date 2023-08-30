Feature: TS53_Create_Deal_Entitlments

 @Regression @TS53
 Scenario Outline:  Create Entitlments
		Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And Create new deal with basic details with given "<TSID>".
		And Create two Accounts with given "<TSID>"
		And Create Parties in the Parties Tab with given "<TSID>"
		And Add entitlements for deal with given "<TSID>"
    Then submit the deal
    Then approve the deal from the deal checker common method
    Then Verify Entitlement in transaction "<TSID>"

Examples:
      |TSID |
      |TS53|