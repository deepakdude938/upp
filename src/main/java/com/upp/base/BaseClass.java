package com.upp.base;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
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
	public static String config_productName ;
	public static	List<String> object ;
	public static	String odpRecordJson;
	public static String dealID_Assertion ;
	public static String url;
	public static String base_url;
	public static String  api_Password;
	public static String virtual_Account_Number;
	public static int usercount2 = 0;
	public static String physical_Account_Number;
	public static int phonenumber = 0;
	public static String debitAccount ;
	public static String endToEndIdRule="";
	public static  String batchId = "";
	public static String endToEndId;
	public static String endToEndIdAssertion="";
	public boolean isFlexibleFunding;
	public static  List<String> accountList ;
	public static  LinkedHashMap<String,String> accountMap ;
	public static String End2EndId;
	

	public WebDriver initialize() throws Exception {
		
		FileInputStream fis = new FileInputStream(Constants.PROJECT_PATH + Constants.PROPERTY_FILE_PATH);
		prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("browser");
//		isHeadLess = true;
		accountList=new LinkedList<String>();
		accountMap=new LinkedHashMap<>();
		String env = prop.getProperty("env");
		switch(env.toLowerCase()) {
		
					case "sit":
						System.out.println("In sit");
						url = prop.getProperty("SIT_Url");
						api_Password= prop.getProperty("api_password");
						break;
						
					case "qa":
						System.out.println("In QA");
						url = prop.getProperty("QA_Url");
						api_Password= prop.getProperty("QA_api_password");
						break;
						
					case "dev":
						System.out.println("In dev");
						url = prop.getProperty("DEV_Url");
						api_Password= prop.getProperty("api_password");
						break;
						
					case "scb":
						System.out.println("In scb");
						url = prop.getProperty("SCB_Url");
						api_Password= prop.getProperty("api_password");
						break;	
		}
		
				base_url=	url.replace("login", "");

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			String downloadFilepath = new File("downloadedFiles").getAbsolutePath();

			if (System.getProperty("os.name").equals("Linux")) {
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				String path=System.getProperty("user.dir")+ "//src//main//resources//chromedriver";
				System.out.println("the path:"+path);
				System.setProperty("webdriver.chrome.driver", path);
				options.addArguments("--no-sandbox");
				options.addArguments("--headless", "--window-size=1296,696", "--disable-gpu",	"--disable-dev-shm-usage");
				options.addArguments("--disable-extensions");
				options.addArguments("--remote-allow-origins=*");
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
				options.addArguments("--remote-allow-origins=*");
				options.setExperimentalOption("prefs", chromePrefs);
				WebDriverManager.chromedriver().setup();
//				WebDriverManager.chromedriver().browserVersion("116.0.5845.97").setup();
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--start-maximized");
				if (isHeadLess) {
					options.addArguments("--headless=new", "--window-size=1296,696", "--no-sandbox", "--disable-gpu",
							"--disable-dev-shm-usage");
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

	public static void applyExplicitWaitsUntilElementVisible(WebElement element, Duration time)
			throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void applyExplicitWaitsUntilElementClickable(WebElement element, Duration time)
			throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void applyExplicitWaitsUntilElementInvisible(String element, Duration i)
			throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, i);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
	}
	
	public static void applyExplicitWaitsUntilElementInvisible(WebElement element, int i)
			throws MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(i));
		 wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	public void applyWaitForDynamicWebElement(By locator, Duration time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void applyExplicitWaitsUntilElementVisible(List<WebElement> element, Duration time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public static void applyExplicitWaitsUntilElementVisible(By locator, int time) {

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
			status = element.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}

		return status;
	}

	public boolean retryUsingWhileLoop_TryCatch(List<WebElement> t) {
		boolean outcome = false;
		int repeat = 0;

		while (repeat <= 3) {

			try {
				driver.findElement(By.xpath(getXpath(t)));
			} catch (StaleElementReferenceException r) {

			}
		}

		return outcome;

	}
	
	public String generateRandomString(int count) {
	String randomString=	UUID.randomUUID().toString().substring(0, count);
	return randomString;
	}

	public String getXpath(List<WebElement> dataServiceNameBifrost) {
		String str = dataServiceNameBifrost.toString();
		String[] listString = null;
		if (str.contains("xpath"))
			listString = str.split("xpath:");

		else if (str.contains("id"))
			listString = str.split("id:");
		String last = listString[1].trim();
		System.out.println(last);
		String xpath = last.split("ChromeDriver")[0];
		return xpath.substring(0, xpath.length() - 5).trim();
	}
}
