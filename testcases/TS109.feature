Feature: TS109

 @Regression @TS109
 Scenario Outline: OnBoard the user and verify responsibility
		Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And OnBoard the user with given role for "<TSID>"
    And Approve the same user
    Then Call the DELETE_USER_ROLE_API with given "<TSID>".
    Then Verify deleted role not displayed "<TSID>".
    
    Examples:	
      |TSID |
      |TS109|
     