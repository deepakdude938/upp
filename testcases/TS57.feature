Feature: TS57

 @Regression @TS57
 Scenario Outline:  Party Responsibilty
		Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And Create new deal with basic details with given "<TSID>".
		And Create two Accounts with given "<TSID>"
		And Create Parties in the Parties Tab with given "<TSID>"
	Then Verify responsibility attributes 

Examples:
      |TSID |
      |TS57|