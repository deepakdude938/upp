package com.upp.hooks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.upp.base.BaseClass;
import com.upp.utils.ExcelReader;
import com.upp.utils.Screenshots;

import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Arrays;
import org.junit.AssumptionViolatedException;

import io.cucumber.java.After;

public class Hook extends BaseClass {

	public static WebDriver driver;
    public static int failCount=0;
	public static int passCount=0;
	public static int skipCount=0;
	public static int totalCount=0;
	public static Screenshots sh;

	@Before()
	public void setUp(Scenario scenario) throws Exception {

		WebDriver driver = initialize();
		this.driver = driver;

		String s[] = scenario.getSourceTagNames().toArray(new String[0]);
		String tagname = s[1];
		String TSIDArray[] = tagname.split("@");
		String TSID = TSIDArray[1];

		System.out.println("The run TSID is" + TSID);

		ExcelReader externalData = new ExcelReader();
		String run = externalData.getFieldData(TSID, "Test Case Master", "Run Mode");

		System.out.println("Run Mode:" + run);

		if ((run.equalsIgnoreCase("no")) || (run.equalsIgnoreCase("n"))) {
			
            skipCount++;      
			throw new AssumptionViolatedException("Skipping test as mentioned in excel sheet");

		}
		
		if(!((run.equalsIgnoreCase("no"))||(run.equalsIgnoreCase("yes"))))
		{
			Assert.fail("Run mode should be Yes/No");
		}

	}

	@After
	public void AfterScenario(Scenario scenario) throws IOException {

//		driver.quit();
		
		String s[] = scenario.getSourceTagNames().toArray(new String[0]);
		String tagname = s[1];
		String TSIDArray[] = tagname.split("@");
		String TSID = TSIDArray[1];
		
		List<Object> list = Arrays.asList(s);
		if(list.contains("@ScbRegression"))
		{
			if(list.contains("@Assertion"))
			{
				TSID=TSID+"_Assertion";
			}
		sh.takeScreenshot_For_SCB_Scenarios(driver,TSID);
		}
		
		if((scenario.isFailed()))
		{
			failCount++;
		}
		if(!(scenario.isFailed()))
		{
			passCount++;
		}
   	driver.close();
	}
	 public static int getPassedCount() {
	        return passCount;
	    }

	    public static int getFailedCount() {
	        return failCount;
	    }
	    public static int getSkippedCount() {
	    	 return skipCount;
	    }

	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			// screenshot
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
			scenario.attach(fileContent, "image/png", "image");
			
			String s[] = scenario.getSourceTagNames().toArray(new String[0]);
			String tagname = s[1];
			String TSIDArray[] = tagname.split("@");
			String TSID = TSIDArray[1];
			List<Object> list = Arrays.asList(s);
		
				if(list.contains("@Assertion"))
				{
					TSID=TSID+"_Assertion";
				}
			sh.takeScreenshot_For_SCB_Scenarios(driver,TSID);
			
			sh=new Screenshots(driver);
			sh.takeScreenshot(driver, TSID);
		

		}

	}

}
