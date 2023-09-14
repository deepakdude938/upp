#Feature: TS89_Party_Apis_Delete_Party_API_In_Deal_Draft_State
#
#@Regression @TS89 @Api
#Scenario Outline: Party_Apis_Delete_Party_API_In_Deal_Draft_State
#Given Open browser and enter url 
#Then Login to the application as "txn_maker"
#And Create new deal with basic details with given "<TSID>".
#And Create two Accounts with given "<TSID>"
#Then submit the deal
#Then approve the deal from the deal checker common method
#And Add the Party through Api call with given "TS89-1"
#And Edit the Live Deal
#Then Call the Delete Party API In Deal Draft State with given "TS89-2"
#And Logout of UPP through api
#Then logout of the application
#Examples:
      #|TSID|
      #|TS89|


