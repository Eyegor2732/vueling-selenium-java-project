package com.vueling.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vueling.CommonComponents.CommonActions;

public class BookPageObjects extends CommonActions{
	
WebDriver driver;
	
	public BookPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="originInput")
	protected
	WebElement originInput;
	
	@FindBy(id="destinationInput")
	protected
	WebElement destinationInput;
	
	@FindBy(id="outboundDate")
	protected
	WebElement outboundDateInput;
	
	@FindBy(id="returnDate")
	protected
	WebElement returnDateInput;
	
	@FindBy(css=".passengers")
	protected
	WebElement passengersInput;
	
	@FindBy(id="reserve-button-summary-flight-angular")
	protected
	WebElement showflightsButton;
	
	@FindBy(xpath="(//*[@class='searchbar-monthpicker_date'])[2]")
	protected
	WebElement originMonth;
	
	@FindBy(xpath="(//*[@class='searchbar-monthpicker_date'])[18]")
	protected
	WebElement returnMonth;
	
	@FindBy(xpath="(//*[contains(@class, 'selected ng-star-inserted')])[2]")
	protected
	WebElement originDay;
	
	@FindBy(xpath="(//*[contains(@class, 'selected ng-star-inserted')])[4]")
	protected
	WebElement returnDay;
	
	@FindBy(css=".form-input_button_searcher")
	protected
	WebElement searchButton;
	
	@FindBy(css="[role='option']")
	protected
	List<WebElement> popupDropdown;
	
	@FindBy(css=".vy-switch button")
	protected
	WebElement calendarSwitchButton;
	
	@FindBy(css="td[aria-disabled='false']")
	protected
	List<WebElement> availableDates;
	
	@FindBy(css="[aria-label='Passengers']")
	protected
	WebElement passengrersInput;
	
	@FindBy(css="[aria-label*='Adults'] [aria-label*='Add']")
	protected
	WebElement adultAddButton;
	
	@FindBy(css="[aria-label*='Adults'] [role='status']")
	protected
	WebElement adultCounter;
	
	@FindBy(xpath="((//button[@aria-pressed=\"true\"])[2]/span/span)[1]")
	protected
	WebElement originMonthButton;
	
	@FindBy(xpath="((//button[@aria-pressed=\"true\"])[3]/span/span)[1]")
	protected
	WebElement returnMonthButton;
	
	@FindBy(xpath="(//a[(@role='cell') and contains(@class, 'selected')])[1]//div[@role='button']")
	protected
	WebElement originDayButton;
	
	@FindBy(xpath="(//a[(@role='cell') and contains(@class, 'selected')])[2]//div[@role='button']")
	protected
	WebElement returnDayButton;
	 
	protected
	By citiesListLocator = By.xpath("(//*[@class='vy-list-dropdown_item_button'])[2]");
	
	protected
	By outboundTitleLocator = By.xpath("//h5[text()=' Choose outbound date']");
	
	protected
	By returnTitleLocator = By.xpath("//h5[text()=' Choose return date']");
	
	protected 
	By originMonthLocator = By.xpath("(//*[contains(@class, 'searchbar-monthpicker')])[1]");
	
	protected 
	By returnMonthLocator = By.xpath("(//*[contains(@class, 'searchbar-monthpicker')])[2]");
	
	protected
	By popupDropdownLocator = By.cssSelector("[role='option']");
	
	protected
	By availableDatesLocator = By.cssSelector("td[aria-disabled='false']");
	
	protected
	By circleLocator = By.xpath("//div[@class='circle']");

}
