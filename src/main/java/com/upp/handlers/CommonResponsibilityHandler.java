package com.upp.handlers;

public class CommonResponsibilityHandler {

	public void handleResponsibility(String callbackid, Object data) throws Exception {
		if (callbackid.equalsIgnoreCase("RESPONSIBILITIES")) {
			String responsibility = (String) data;
			if (responsibility.equalsIgnoreCase("Merchant")) {
				DealResponsibilityHandler responsibilityHandler = new DealResponsibilityHandler();
				responsibilityHandler.handleMarchant();
			}
			else	if (responsibility.equalsIgnoreCase("Acquiree")) {
				DealResponsibilityHandler responsibilityHandler = new DealResponsibilityHandler();
				responsibilityHandler.handleAcquiree();
			}
			else	if (responsibility.equalsIgnoreCase("AutomationAttributes")) {
				DealResponsibilityHandler responsibilityHandler = new DealResponsibilityHandler();
				responsibilityHandler.handleAutomationAttributes();
			}
			else	if (responsibility.equalsIgnoreCase("Buyer")) {
				DealResponsibilityHandler responsibilityHandler = new DealResponsibilityHandler();
				responsibilityHandler.handleBuyer();
			}
			else	if (responsibility.equalsIgnoreCase("Seller")) {
				DealResponsibilityHandler responsibilityHandler = new DealResponsibilityHandler();
				responsibilityHandler.handleSeller();
			}
		}
	}
}
