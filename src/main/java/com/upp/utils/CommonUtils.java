package com.upp.utils;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtils {
	WebDriver driver;
	
	public CommonUtils(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isElementDisplayed(WebElement Element,int time) {
	   boolean state=false;
	    try {
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	        state= Element.isDisplayed();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    } 
	    catch (NoSuchElementException e) {
	    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    }
	    return state;
	}
}
