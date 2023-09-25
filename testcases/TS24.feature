Feature: TS24

 @Regression @TS24
 Scenario Outline: OnBoard the user and verify responsibility
		Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And OnBoard the user with given role for "<TSID>"
    And Approve the same user
    Then Logout from Application
    	Then Enter URL
    Then login with updated user using "<UserID>"
    Then Verify user is able to login
    Then Check responsibility
		Then logout of the application 
		Then Enter URL
		Then  Login to the application as "deal_maker"
		Then Deactivate the user using "<UserID>"
		And Approve the same user
		 Then Logout from Application
		 	Then Enter URL
		 Then login with updated user using "<UserID>"
		 Then Verify user is not  able to login
    Examples:	
      |TSID |UserID|
      |TS24|TS24-1|
     