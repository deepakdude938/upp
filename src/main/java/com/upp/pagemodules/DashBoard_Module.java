package com.upp.pagemodules;



import com.upp.base.BaseClass;
import com.upp.utils.DropDown;
import com.upp.pageobjects.Object_Deal;
import com.upp.utils.ExcelReader;

public class DashBoard_Module extends BaseClass{
	
	public static Object_Deal od ;
//	public static Properties prop;
	public static ExcelReader externalData;
	public static DropDown dropdown;

	public DashBoard_Module() {
		
		od = new Object_Deal();
		externalData = new ExcelReader();
		dropdown=new DropDown(driver);

	}

	public void loginToUPP() {
		
		od.username.sendKeys(prop.getProperty("username"));
		od.password.sendKeys(prop.getProperty("password"));
		od.loginIn.click();	
	
	}

	public void createNewDeal(Integer TcId) throws Exception{
	
			 od.deal_SideMenuIcon.click();
			 od.newDealButton.click();
			 od.newDeal.sendKeys(externalData.getFieldData(TcId,"Basic Details","Deal Name"));
			 dropdown.selectByVisibleText(od.businessSegmentDropDown, externalData.getFieldData(TcId,"Basic Details","Business Segment"));
			 dropdown.selectByVisibleText(od.countryIndiaDropDown, externalData.getFieldData(TcId,"Basic Details","Country"));
		 
	}
	
	
	

}
