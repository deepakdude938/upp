package com.upp.handlers;

public class CommonLinkedInstructionInstrument {

	DealLinkedInstruction_PaymentInstrumentHandler instrumentHandler = new DealLinkedInstruction_PaymentInstrumentHandler();
	
	public void handleInstruments(String TSID,String callbackid, Object data, String toAccount) throws Exception {
		if (callbackid.equalsIgnoreCase("Instrument_NAME")) {
			String instrument = (String) data;
			if (instrument.equalsIgnoreCase("BT_IN")) {
				instrumentHandler.handlePartyMakerBT_INPaymentInstrument(TSID, toAccount);
			}
		}
	}
}
