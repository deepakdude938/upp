#Feature: TS83_Rule_OBOParticipant_OBO_Info_Null_OBO_VirtualAccount
#
#@Regression @TS83 @InitiationRules
#Scenario Outline: TS83_Rule_OBOParticipant_OBO_Info_Null_OBO_VirtualAccount
#Given Open browser and enter url 
#Then Login to the application as "txn_maker"
#And Create new deal with basic details with given "<TSID>".
#And Call the ODP Login Api
#Then Create account in Odp with details from excel sheet with given "<TSID>".
#Then Call the ODP Logout Api
#And Create Account_One From excel sheet with given "<TSID>".
#Then submit the deal
#Then approve the deal from the deal checker common method
#Then Open and Edit the live deal
#Then Click On Accounts Tab
#And Call the ODP Login Api
#Then Create Virtual account in ODP with given "TS83_Virtual".
#Then Call the ODP Logout Api
#And OnBoard the Virtual account with given "TS83_Virtual".
#Then Add Party basic_Details with given "<TSID>".
#Then Click On Accounts Tab
#Then Add Party basic_Details with given "TS83_1".
#Then Add Party Accounts with given "TS83_1".
#Then submit the deal
#Then approve the deal from the deal checker common method
#And Login to UPP through api
#And Call the Rule_ParticipantId_OBODetails_Virtual Api with given "<TSID>".
#And Logout of UPP through api
#Examples:
      #|TSID|
      #|TS83|


