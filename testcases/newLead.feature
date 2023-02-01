Feature: UPP feature


@Regression
Scenario Outline: Create Basic details with TC1
Given User is on LoginPage
Then Login to the application
And Create new deal POC with basic details with given <TcId>.
Examples:
      |TcId|
      |1   |
      
      
      
#@Regression
#Scenario Outline: Create Basic details with TC2
#Given User is on LoginPage
#Then Login to the application
#And Create new deal POC with basic details with given <TcId>.
#Examples:
      #|TcId|
      #|2   |

