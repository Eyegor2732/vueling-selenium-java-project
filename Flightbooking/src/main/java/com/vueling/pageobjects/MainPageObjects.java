package com.vueling.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vueling.CommonComponents.CommonActions;

public class MainPageObjects extends CommonActions{
	
WebDriver driver;
	
	public MainPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="onetrust-accept-btn-handler")
	protected
	WebElement acceptCookiesButton;

}
