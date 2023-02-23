package com.upp.handlers;

public class CommonResponsibilityHandler {

	public void handleResponsibility(String callbackid, Object data) {
		if (callbackid.equalsIgnoreCase("RESPONSIBILITIES")) {
			String responsibility = (String) data;
			if (responsibility.equalsIgnoreCase("Merchant")) {
				DealResponsibilityHandler responsibilityHandler = new DealResponsibilityHandler();
				responsibilityHandler.handleMarchant();
			}
		}
	}

}
