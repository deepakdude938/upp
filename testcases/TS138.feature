Feature: TS138_External Introductory mail Setup

@Regression @TS138 @WeeklyMon
Scenario Outline: External Introductory mail Setup
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Send Introductory Mail
Then Verify Email id In ODP "<TSID>"

Examples:
      |TSID   |
      |TS138   |


