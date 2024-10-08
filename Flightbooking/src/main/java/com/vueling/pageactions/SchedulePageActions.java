package com.vueling.pageactions;

import org.openqa.selenium.WebDriver;

import com.vueling.pageobjects.SchedulePageObjects;

public class SchedulePageActions extends SchedulePageObjects{
	
	WebDriver driver;

	public SchedulePageActions(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public String actualOriginMonthText = "";
	public String actualOriginDayText = "";	
	public String actualReturnMonthText = "";
	public String actualReturnDayText = "";

	public PassengersPageActions confirmFlight() throws InterruptedException {
		waitForURL("ScheduleSelectNew", 5);
		actualOriginMonthText = originMonthButton.getText();
		actualOriginDayText = originDayButton.getText();	
		actualReturnMonthText = returnMonthButton.getText();
		actualReturnDayText = returnDayButton.getText();		
		outboundTicket.get(0).click();
		returnTicket.get(0).click();
		waitforElementToDisappear(circleLocator, 5);
		flyButton.click();
		Thread.sleep(1000);
		continueButton.click();

		return (new PassengersPageActions(driver));
	}

} 