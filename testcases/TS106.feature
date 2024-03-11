Feature: TS106_Payment_Alert_Instruction_Message

@Regression  @TS106 @MonWedFri
 Scenario Outline:  Create deal with alerts Message
			Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And Create new deal with basic details with given "<TSID>".
		And Create two Accounts with given "<TSID>"
		And Add Alert for payment with given "<TSID>"
		Then submit the deal
		Then approve the deal from the deal checker common method
		 Then Open and Edit the live deal
		Then Edit the alert and verify message is displayed

Examples:
      |TSID |
      |TS106|