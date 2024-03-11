Feature: TS107_Account_Amendment_With_Transaction_UI

@Api @TS107 @TuesThurs
Scenario Outline: Account_Amendment_With_Transaction_UI
Given Open browser and enter url 
Then Login to the application as "txn_maker"
And Create new deal with basic details with given "<TSID>".
And Create two Accounts with given "<TSID>"
And Create two Accounts with given "<TSID>"
Then Add Party basic_Details with given "<TSID>".
Then Hit Back button
Then Add Party Basic Details with given "TS107_1".
Then Add Party Accounts with given "<TSID>".
Then Add Party Accounts with given "TS107_1".
And Submit the deal
Then Approve the deal from the deal checker
Then Call the Create Transaction Api for "<TSID>"
Then Validate in execution report for scheduled record using endToEndId "<TSID>"
Then Edit party account with given "TS107_1" from Party Maker tab
Then Submit Party with given "<TSID>"
Then Approve the Party with given "<TSID>"
Then Validate in execution report for scheduled record using endToEndId "<TSID>"
Then Edit the deal "<TSID>"
Then Verify Account is updated
Then Submit Party with given "<TSID>"
Then Approve the Party with given "<TSID>"
Then Attempt to offboard the account "<TSID>"
Examples:
      |TSID   |
      |TS107|