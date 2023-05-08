Feature: TS24

 @Regression @TS24

    Scenario Outline: Verify the updated user 
    Given Open browser and enter url
		Then login with updated user using "<TSID>"
		Then Verify user is able to login
		Then Check responsibility
		
		
    Examples:
      |TSID |
      |TS24|	
      
      	
