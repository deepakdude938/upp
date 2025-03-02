 Feature: TS54_Entitlements-Account
 
 @Regression @TS54 @TuesThurs
 Scenario Outline:  Create Entitlments-Account
		Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And Create new deal with basic details with given "<TSID>".
		And Create two Accounts with given "<TSID>"
		And Create Parties in the Parties Tab with given "<TSID>"
		And Add account-entitlements for deal with given "<TSID>"
    Then submit the deal
    Then approve the deal from the deal checker common method
    Then Verify Entitlement in transaction "<TSID>"
Examples:
      |TSID |
      |TS54|