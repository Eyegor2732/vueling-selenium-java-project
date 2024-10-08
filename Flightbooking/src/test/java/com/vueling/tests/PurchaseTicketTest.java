package com.vueling.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vueling.TestComponenets.BaseTest;
import com.vueling.TestComponenets.Retry;
import com.vueling.pageactions.BagsservicePageActions;
import com.vueling.pageactions.BookPageActions;
import com.vueling.pageactions.PassengersPageActions;
import com.vueling.pageactions.PaymentPageActions;
import com.vueling.pageactions.SchedulePageActions;
import com.vueling.pageactions.SeatservicePageActions;
import com.vueling.pageactions.ServicesPageActions;

public class PurchaseTicketTest extends BaseTest {

	@Test(dataProvider = "getData", retryAnalyzer = Retry.class)
	public void purchaseTicketTest(HashMap<String, String> input) throws InterruptedException {
		BookPageActions bookpageactions = mainpageactions.clickBookNow();

		SchedulePageActions schedulepagections = bookpageactions.findFlight(input.get("originSearch"),
				input.get("originResult"), input.get("destinationSearch"), input.get("destinationResult"),
				input.get("adultPassengers"));

		PassengersPageActions passengerspageactions = schedulepagections.confirmFlight();

		Assert.assertEquals(schedulepagections.actualOriginDayText, bookpageactions.expectedOriginDayText);
		Assert.assertTrue(
				schedulepagections.actualOriginMonthText.equalsIgnoreCase(bookpageactions.expectedOriginMonthText));
		Assert.assertEquals(schedulepagections.actualReturnDayText, bookpageactions.expectedReturnDayText);
		Assert.assertTrue(
				schedulepagections.actualReturnMonthText.equalsIgnoreCase(bookpageactions.expectedReturnMonthText));

		SeatservicePageActions seatservicepageactions = passengerspageactions.enterPaxInformation(input.get("name"),
				input.get("surname"), input.get("country"), input.get("phone"), input.get("email"));

		BagsservicePageActions bagsservicepageactions = seatservicepageactions.selectSeat();

		ServicesPageActions servicespageactions = bagsservicepageactions.selectLuggage();

		PaymentPageActions paymentpageactions = servicespageactions.selectServices();

		paymentpageactions.payNow(input.get("cardNumber"), input.get("cardType"), input.get("cardHolder"),
				input.get("expiry"), input.get("cvv"));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> jsonList = getJsonDataToMap(
				userDir + "/src/test/java/com/vueling/data/PurchaseOrder.json");

		int datasize = jsonList.size();
		Object[][] data = new Object[datasize][1];
		for (int i = 0; i < datasize; i++) {
			data[i][0] = jsonList.get(i);
		}

//		Object[][] data = new Object[][] { { jsonList.get(0) } };

		return data;
	}

}