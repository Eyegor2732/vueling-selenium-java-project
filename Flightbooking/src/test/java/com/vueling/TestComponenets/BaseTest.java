package com.vueling.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vueling.pageactions.MainPageActions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends OSUtils {
	public WebDriver driver;
	public MainPageActions mainpageactions;
	protected ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>(); // declared Threadlocal instance

	private WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				userDir + "/src/main/java/com/vueling/resources/GlobalData.properties");
		prop.load(fis);

		String browserName = systemBrowser != null ? systemBrowser : prop.getProperty("browser");

		switch (browserName.toLowerCase()) {
		case "cromeheadless" -> {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		case "chrome" -> {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		case "firefox" -> {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		case "edgeheadless" -> {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("headless");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		case "edge" -> {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		case "safari" -> {
			if (isWindows()) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else {
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
			}
		}
		default -> {
			if (isWindows()) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (isMac()) {
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
			} else {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
		}
		}

		driver.manage().window().setSize(new Dimension(1700, 1400));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String filePath = userDir + "/reports/" + testCaseName + ".png";
		File file = new File(filePath);
		FileUtils.copyFile(src, file);

		return filePath;
	}

	// Extent reports

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read Json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// string to HashMap - need mvn dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});

		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public MainPageActions launchApp() throws IOException {
		driver = initializeDriver();
		mainpageactions = new MainPageActions(driver);
		mainpageactions.goTo();
		// Store the WebDriver instance in the ThreadLocal variable
  		threadDriver.set(driver);
		return mainpageactions;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		// driver.quit(); // it's simple version without ThreadLocal
		// Fetch the WebDriver instance and close it
		threadDriver.get().quit();
		// Remove the WebDriver instance from the ThreadLocal variable
		threadDriver.remove();
	}

}
