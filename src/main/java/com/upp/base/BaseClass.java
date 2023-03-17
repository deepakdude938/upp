package com.upp.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static String browser = System.getProperty("browser");
	public static boolean isHeadLess;
	public static Properties prop;
	public static String dealId = "";
	
	public boolean isFlexibleFunding;
	
	public WebDriver initialize() throws Exception {
		
		FileInputStream	fis = new FileInputStream(Constants.PROJECT_PATH+Constants.PROPERTY_FILE_PATH);
		prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("browser");
		isHeadLess=false;
		
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			String downloadFilepath=new File("downloadedFiles").getAbsolutePath();
			
			if(System.getProperty("os.name").equals("Linux")) {
				  HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

				WebDriverManager.chromedriver().setup();
				
					options.addArguments("--no-sandbox");
					options.addArguments("--headless", "--window-size=1296,696",  "--disable-gpu", "--disable-dev-shm-usage");
					options.addArguments("--disable-extensions");
				    options.addArguments("--disable-software-rasterizer");
				    options.addArguments("--disable-notifications");
				    chromePrefs.put("plugins.always_open_pdf_externally", true);
				    chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadFilepath);
					options.setExperimentalOption("prefs", chromePrefs);
			}
			
			else {
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilepath);
				options.setExperimentalOption("prefs", chromePrefs);
				WebDriverManager.chromedriver().setup();
//				WebDriverManager.chromedriver().version("111.0.5563.64").setup();
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--start-maximized");
				if(isHeadLess) {
					options.addArguments("--headless","--window-size=1296,696", "--no-sandbox", "--disable-gpu", "--disable-dev-shm-usage");
				}
			}
			
			driver = new ChromeDriver(options);

			
		} else if (browser.equalsIgnoreCase("firefox")) {
			 
			driver.manage().window().maximize();
			
		} else if (browser.equalsIgnoreCase("internetExplorer")) {
			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			
		}  
		
		else {
			System.err.println("Please pass the correct browser value");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		return driver;

	}
		
	public static void applyExplicitWaitsUntilElementVisible(WebElement element, Duration time)throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void applyExplicitWaitsUntilElementClickable(WebElement element, Duration time)throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void applyExplicitWaitsUntilElementInvisible(String element, Duration i)throws MalformedURLException {
	   	WebDriverWait wait = new WebDriverWait(driver, i);
		wait.until( ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
	}
	
	public void applyWaitForDynamicWebElement(By locator, Duration time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void applyExplicitWaitsUntilElementVisible(List<WebElement> element, Duration time) {
				WebDriverWait wait = new WebDriverWait(driver, time);
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void applyExplicitWaitsUntilElementVisible(By locator, int time) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void handleElementClickException(WebElement element) {

		
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

		}
		javascriptExecutor.executeScript("arguments[0].click()", element);

	}

	public boolean isWebElementDisplayed(WebElement element) {
		boolean status;
		try {
			 status =element.isDisplayed();
		}
		catch(NoSuchElementException e) {
			 status =false;
		}
		
		return status;
	}
	
	public boolean retryUsingWhileLoop_TryCatch(List<WebElement> t) {
		boolean outcome = false;
		int repeat = 0;
		
		while(repeat<=3) {
			
			try {
				driver.findElement(By.xpath(getXpath(t)));
			}
			catch(StaleElementReferenceException r) {
				
			}
		}
		
		return outcome;
		
	}
	
		public String getXpath(List<WebElement> dataServiceNameBifrost) {
	    String str = dataServiceNameBifrost.toString();
	    String[] listString = null;
	    if(str.contains("xpath"))
	      listString = str.split("xpath:");
	    
	    else if(str.contains("id"))
	      listString = str.split("id:");
	    String last = listString[1].trim();
	    System.out.println(last);
	    String xpath= last.split("ChromeDriver")[0];
	    return xpath.substring(0, xpath.length() - 5).trim();
	}
}
