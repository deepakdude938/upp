package com.upp.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.upp.base.*;

public class WaitTypes extends BaseClass{

//	WebDriver driver;
	WebDriverWait wait;

	public WaitTypes(WebDriver driver) {
		this.driver = driver;
	}


	public WebElement waitForElementToBeClickable(WebElement Element, int timeout) {
		Actions g =new Actions(driver);
		try {
		
			wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(Element));
			wait.until(ExpectedConditions.elementToBeClickable(Element));
			g.moveToElement(Element);
			
		} catch (Exception e) {
			e.getMessage();
		}

		return Element;
	}

	public WebElement waitforElementToBeDisplayed(WebElement Element, int timeout) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(Element));
		} catch (Exception e) {

		}
		return Element;
	}
	
	public WebElement waitforPresenceOfElementLocated(WebElement Element, int timeout) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(Element)));
		} catch (Exception e) {

		}
		return Element;
	}
	
	

}