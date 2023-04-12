package com.upp.utils;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollTypes {

	static WebDriver driver;

	public ScrollTypes(WebDriver driver) {
		this.driver = driver;
	}

	public void scrollDown() throws AWTException {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void scrollUp() throws AWTException {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	}

	public void scrollByParameter(int x, int y) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(" + x + "," + y + ")");
	}

	public static void scrollInToView(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public boolean verifyScroller() {
		String execScript = "return document.documentElement.scrollHeight>document.documentElement.clientHeight;";
		JavascriptExecutor scrollBarPresent = (JavascriptExecutor) driver;
		Boolean test = (Boolean) (scrollBarPresent.executeScript(execScript));
		return test;
	}
	
	public static void  scrollInsideWindow(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTop = arguments[1];",element, 1000);
	
	}
	
	public static void  scrollHorizontalInsideWindow(WebElement element,int pixel){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollLeft = arguments[1];",element, pixel);
	
	}
	
	public static void  scrollInsideWindow1(WebElement element,int pixelNo){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTop = arguments[1];",element, pixelNo);
	
	}
	
	public static void scrollInsideWindowTillWebElementPresent(WebElement elementToBeLocated,WebElement window,int maxRepeatCount,int pixel) {
		CommonUtils b = new CommonUtils(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int i=0;
		while(!b.isElementDisplayed(elementToBeLocated,3) && i!=maxRepeatCount) {
		
			js.executeScript("arguments[0].scrollBy("+pixel+", 0)", window);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}	
}