package com.upp.pagemodules.payment;

public class Retention_Surplus {
	
	public Payment_BasicDetails ins;
	public Retention ret;
	public Retention_Surplus() {
		ins=new Payment_BasicDetails();
		ret=new Retention();
	}
	
	public void createRetentionSurplus(String tsid) throws Exception {
		ins.selectInstruction(tsid);
		ret.createRetentionSurplus(tsid);
	}
}
