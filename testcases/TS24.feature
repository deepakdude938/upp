Feature: TS24

 @Regression @TS24
 Scenario Outline: OnBoard the user and verify responsibility
		Given Open browser and enter url
    Then Login to the application as "deal_maker"
    And OnBoard the user with given role for "<TSID>"
    And Approve the same user
 Then Logout from Application
        #Then Clear and close the browser
          #
    #Examples:
      #|TSID |
      #|TS24|
      #
             @Regression @TS24  	
           	 Scenario Outline: Verify the updated user 
    Given Open browser and enter url
		Then login with updated user using "<TSID>"
		Then Verify user is able to login
#		Then Check responsibility
#		Then logout of the application 
        #Then Clear and close the browser
				
    Examples:
      |TSID |
      |TS24|	
      
     @Regression @TS24 
		Scenario Outline: Deactivate  user 
    Given Open browser and enter url
		Then  Login to the application as "deal_maker"
		Then Deactivate the user using "<TSID>"
		  And Approve the same user
#		    Then Clear and close the browser		
		
    Examples:
      |TSID |
      |TS24-1|		      
      
     
  
          @Regression @TS24
          Scenario Outline: Verify the updated user 
    Given Open browser and enter url
		Then login with updated user using "<TSID>"
		Then Verify user is not  able to login
			 Examples:
      |TSID |
      |TS24-1|	
      
