Feature: TC01


@Regression @TC01
Scenario Outline: Create Basic details with TC01
#Given User is on LoginPage 
#Then Login to the application

Given Open browser and enter url 
Then Login to the application as "deal_maker"
And Create new deal with basic details with given "<TSId>".
Examples:
      |TSId|
      |TS01|
     
      
      
      
#@Regression
#Scenario Outline: Create Basic details with TC2
#Given User is on LoginPage
#Then Login to the application
#And Create new deal POC with basic details with given <TcId>.
#Examples:
      #|TcId|
      #|2   |

