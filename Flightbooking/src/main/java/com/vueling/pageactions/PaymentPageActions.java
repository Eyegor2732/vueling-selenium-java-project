package com.vueling.pageactions;

import org.openqa.selenium.WebDriver;

import com.vueling.pageobjects.PaymentPageObjects;

public class PaymentPageActions extends PaymentPageObjects{
	
	WebDriver driver;

	public PaymentPageActions(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void payNow(String cardNumber, String cardType, String cardHolder, String expiry, String cvv) throws InterruptedException {
		waitForURL("Payment", 10);
		cardnumberInput.sendKeys(cardNumber);
//		cardtypeInput.sendKeys(cardType);
		cardholderInput.sendKeys(cardHolder);
		expiryInput.sendKeys(expiry);
		cvvInput.sendKeys(cvv);
		legalCheckbox.click();
		continueButton.click();
	}

}