package com.upp.hooks;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.upp.base.BaseClass;
import com.upp.utils.ExcelReader;

import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.AssumptionViolatedException;

import io.cucumber.java.After;

public class Hook extends BaseClass {

	public static WebDriver driver;

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
		String run = externalData.getFieldData(TSID, "Test Case Master", "Run TC");

		System.out.println("Run Mode:" + run);

		if ((run.equalsIgnoreCase("no")) || (run.equalsIgnoreCase("n"))) {

			throw new AssumptionViolatedException("Skipping test as mentioned in excel sheet");

		}

	}

	@After
	public void AfterScenario() throws IOException {

//		driver.quit();
	//	driver.close();

	}

	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {
			// screenshot
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
			scenario.attach(fileContent, "image/png", "image");

		}

	}

}
