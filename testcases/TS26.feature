Feature: TS26_Processing Unit

@Regression @TS26
Scenario Outline: Create Processing Unit From Configuration Tab and Verify The PU is Available in BasicDetails
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create Processing Unit from Configuration tab with given "<TSID>"
Then Approve the PU from Admin Checker
Then logout of the application
Then Login to the application as "txn_checker"
And Verify the created PU is available in Basic Details Page with given "<TSID>"
Then logout of the application
Examples:
      |TSID|
      |TS26|
