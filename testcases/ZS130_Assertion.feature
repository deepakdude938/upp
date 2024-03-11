Feature: TS130_Future_Dated_Adhoc_Transaction_ with_GB_Account_Assertion

@Assertion @TS130 @Regression @MonWedFri
Scenario Outline: TS130_Future_Dated_Adhoc_Transaction_ with_GB_Account_Assertion
Given Open browser and enter url 
Then Login to the application as "txn_maker"
Then Fetch record from ODP with "<TSID>"
Then Approve the Transaction from Tnx Checker with given "<TSID>"
Then logout of the application
Given Open browser and enter url 
Then Login to the application as "txn_checker"
And Verify in execution Report One Tnx is Scheduled and other Cancelled with given "<TSID>"
Examples:
      |TSID |
      |TS130|