package com.upp.handlers;

public class CommonDocumentTypeHandler {

	public void handleDocumentType(String callbackid, Object data,String TSID) throws Exception {
		if (callbackid.equalsIgnoreCase("Document_Type")) {
			String doc = (String) data;
			if (doc.equalsIgnoreCase("Blueprint")) {
				DocumentHandler handleDoc = new DocumentHandler();
				handleDoc.handleBlueprintDocument(TSID, doc);
			}
		}
	}
}
