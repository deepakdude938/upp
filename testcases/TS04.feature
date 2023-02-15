Feature: TS04


@Regression @TS04
Scenario Outline: Create deal with Linked Instruction payment
Given User is on LoginPage 
Then Login to the application
And Create new deal with basic details with given "<TcId>".
And Added Onboarding Account with given  "<TcId>".
And Added Parties with given  "<TcId>".
Then create Linked Instruction Payment with given  "<TcId>".
 
Examples:
      |TcId|
      |TS04|