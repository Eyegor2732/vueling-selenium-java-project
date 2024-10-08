package com.vueling.pageactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vueling.pageobjects.BookPageObjects;

public class BookPageActions extends BookPageObjects{
	
	WebDriver driver;
	
	public BookPageActions(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void goTo() {
		driver.get("https://www.vueling.com/en");
	}
	
	private void popupDropdownSelect(String result) {
		for (WebElement element : popupDropdown) {
			if(element.getText().split(",")[0].equalsIgnoreCase(result)) {
				element.click();
				break;
			};
		}
	}
	
	private void calendarTwoWaySwitch() {
		if (calendarSwitchButton.getAttribute("aria-pressed").equals("false")) {
			calendarSwitchButton.click();
		}
	}
	
	public void addAdults(String adultPassengers) {
		int i = 1;
		while (Integer.parseInt(adultCounter.getText()) < Integer.parseInt(adultPassengers)) {
			adultAddButton.click();
			i++;
		}
	}
	
	public String expectedOriginMonthText = "";
	public String expectedOriginDayText = "";	
	public String expectedReturnMonthText = "";
	public String expectedReturnDayText = "";
	
	public SchedulePageActions findFlight(String originSearch, String originResult, String destinationSearch, String destinationResult, String adultPassengers) throws InterruptedException {	
		waitForURL("find-your-flight", 5);
		originInput.click();
		originInput.sendKeys(originSearch);			
		popupDropdownSelect(originResult);
		destinationInput.click();
		destinationInput.sendKeys(destinationSearch);
		waitForElementToAppear(citiesListLocator, 5);
		popupDropdownSelect(destinationResult);
		calendarTwoWaySwitch();
		waitForElementToAppear(outboundTitleLocator, 5);
		Thread.sleep(1000);	
		waitforElementToDisappear(circleLocator, 5);
		originMonth.click();
		waitForElementToAppear(returnTitleLocator, 5);
		returnMonth.click();
		passengrersInput.click();
		addAdults(adultPassengers);
		searchButton.click();
		searchButton.click();
		originDay.click();
		returnDay.click();	
		expectedOriginMonthText = originMonthButton.getText();
		expectedOriginDayText = originDayButton.getText();	
		expectedReturnMonthText = returnMonthButton.getText();
		expectedReturnDayText = returnDayButton.getText();

		showflightsButton.click();
		
		return (new SchedulePageActions(driver));
	}

}