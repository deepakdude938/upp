Feature: TS24

 @Regression @TS24
 Scenario Outline: OnBoard the user and verify responsibility
		Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And OnBoard the user with given role

Examples:
      |TSID |
      |TS22|