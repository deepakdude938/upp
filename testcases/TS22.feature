Feature: TS22_Payment_Alert_Instruction

 @Regression @TS22 @MonWedFri
 Scenario Outline:  Create deal with alerts
		Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And Create new deal with basic details with given "<TSID>".
		And Create two Accounts with given "<TSID>"
		And Add Alert for payment with given "<TSID>"
		Then submit the deal
Then approve the deal from the deal checker common method
And Check the Transaction staus and instruction type in execution report with given "<TSID>"

Examples:
      |TSID |
      |TS22|