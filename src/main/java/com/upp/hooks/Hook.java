package com.upp.hooks;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.upp.base.BaseClass;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import io.cucumber.java.After;


public class Hook extends BaseClass {
	
	
	
//	@Before()
//	public void setUp() throws Exception {
//		initialize();
//		
//	}
	
	public static WebDriver driver;
	@Before()
	public void setUp() throws Exception {
		WebDriver driver=initialize();
		this.driver=driver;
		
		
	}
	
	@After
	public void AfterScenario() throws IOException
	{
		
		driver.quit();
		
	}
	
	
	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException
	{
		
		if(scenario.isFailed())
		{
		//screenshot
		File sourcePath= 	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
		scenario.attach(fileContent, "image/png", "image");
		
		}
		
	}
	
}
	
