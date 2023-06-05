package com.upp.base;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

public class Test {
public static void main(String[] args) throws ParseException {
	
	String res = "{\"dealrefId\":\"REF1685956338495\",\"errors\":[{\"code\":\"SC_ERR_OBO_DETAILS_PARTICIPANT\",\"message\":\"Debit account is configured as ParticipantId, but partyRefId is not passed in the request\"},{\"code\":\"SC_ERR_NULL_PARTICIPANTID_DEBT\",\"message\":\"partyRefId is mandatory when dealRefId is passed in the request\"}],\"platformRefNo\":\"Party7cad39\"}\r\n"
			+ "";
	
	System.out.println(res);
	
	JSONParser jsonParser = new JSONParser();
	Object	object = jsonParser.parse(res);
	JSONObject 	jsonObject = (JSONObject) object;
	JSONArray er=	(JSONArray) jsonObject.get("errors");
String codemees="SC_ERR_NULL_PARTICIPANTID_DEBT";
String messa = "partyRefId is mandatory when dealRefId is passed in the request";
int errorCount = 0;
if(er.size()>1) {
	for (int i = 0; i < er.size(); i++) {
		 JSONObject obj = (JSONObject) er.get(i);
String a =	(String) obj.get("code");
String b =(String) obj.get("message");
		
		  if(codemees.equalsIgnoreCase(a)  && messa.equalsIgnoreCase(b)) {
			  errorCount++;
			  break;
		  }
		 
	}
	if(errorCount==0) {
		Assert.assertTrue(false);
	}
	
}
}
}
