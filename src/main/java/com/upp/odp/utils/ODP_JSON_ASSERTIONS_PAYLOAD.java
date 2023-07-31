package com.upp.odp.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upp.base.BaseClass;
import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.stepdefinition.TS06;
import com.upp.utils.Property;

public class ODP_JSON_ASSERTIONS_PAYLOAD extends BaseClass{

	public void Odp_Json_for_TS70(String TSID) throws Exception  {

		HashMap odpRecord = new HashMap<>();
		odpRecord.put("_id", TSID);
		odpRecord.put("originTcId", TSID);
		odpRecord.put("dealId", TS06.dealId);
		odpRecord.put("dealRefId", TS06.dealId);

		ObjectMapper mapper = new ObjectMapper();
		HashMap jsonMap1 = new HashMap();
		odpRecordJson = new ObjectMapper().writeValueAsString(odpRecord);
		System.out.println(odpRecordJson);
	}
}
