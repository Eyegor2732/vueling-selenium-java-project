package com.vueling.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vueling.CommonComponents.CommonActions;

public class PaymentPageObjects extends CommonActions{
	
WebDriver driver;
	
	public PaymentPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[id$='ACCTNO']")
	protected
	WebElement cardnumberInput;
	
	@FindBy(css="input[id*='Combobox']")
	protected
	WebElement cardtypeInput;
	
	@FindBy(css="[id='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VR_CC::AccountHolderName']")
	protected
	WebElement cardholderInput;
	
	@FindBy(css="[id='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VR_EXPDAT_MONTH_YEAR']")
	protected
	WebElement expiryInput;
	
	@FindBy(css="[id='CONTROLGROUPPAYMENTBOTTOM_ControlGroupPaymentInputViewPaymentView_ExternalAccount_VR_CC::VerificationCode']")
	protected
	WebElement cvvInput;
	
	@FindBy(css="[for*=\"CheckboxConditionsPaymentView\"]")
	protected
	WebElement legalCheckbox;
	
	@FindBy(css="#ECDccAcceptedYes")
	protected
	WebElement currencyRadio;
	
	@FindBy(css="[id$='LinkButtonSubmit']")
	protected
	WebElement continueButton;

	protected
	By currencyRadioLocator = By.cssSelector("[for='ECDccAcceptedYes']");

}
