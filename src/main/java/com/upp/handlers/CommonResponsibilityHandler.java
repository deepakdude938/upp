package com.upp.handlers;

public class CommonResponsibilityHandler {

	public void handleResponsibility(String callbackid, Object data) throws Exception {
		if (callbackid.equalsIgnoreCase("RESPONSIBILITIES")) {
			String responsibility = (String) data;
			if (responsibility.equalsIgnoreCase("Merchant")) {
				DealResponsibilityHandler responsibilityHandler = new DealResponsibilityHandler();
				responsibilityHandler.handleMarchant();
			}
			if (responsibility.equalsIgnoreCase("Acquiree")) {
				DealResponsibilityHandler responsibilityHandler = new DealResponsibilityHandler();
				responsibilityHandler.handleAcquiree();
			}
		}
	}
}
