package com.training.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

public class TestBaseUtil {

	public  WebDriver driver;

	public  void lanuchApplication(String appURL) {

		// set the chromedriver.exe path to launch chrome browser
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");// driver
		// open the chrome browser

		driver = new ChromeDriver();
		// ChromeDriver driver = new ChromeDriver();
		// open the application url in browser
		driver.get(appURL);
		// driver.navigate().to(appURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * Method to get today date and time
	 * 
	 * @return
	 */
	public String getTodaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("M_d_yyyy_h_mm_ss");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String todaysdate = dateFormat.format(date);
		System.out.println("Today's Date:" + todaysdate);
		return todaysdate;
	}

	public void captureScreenshot() throws InterruptedException, IOException {

		// Converting webdriver object into Takescreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		// caputure the screenshot
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		// move the captured screenshot into destination folder path
		File destFile = new File("screenshots\\"+this.getClass().getSimpleName()+"_" + getTodaysDate() + ".png");
		// File destFile = new File("screenshots\\"+this.getClass().getSimpleName() +
		// getTodaysDate() + ".png");
		FileUtils.copyFile(srcFile, destFile);

	}
	
	/**
	 * Method to connect saucelabs
	 * @param os
	 * @param browser
	 * @throws Exception
	 */
	public void runOnSauceLabsFromLocal(String os, String browser) throws Exception {

		String username = "autotestjavaselenium";
		String access_key = "3cab11e15-b5f0-4f2c-8afa-1152ec69f3d3";
	    String url = "https://" + username + ":" + access_key + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";

		//String url ="https://autotestjavaselenium:cab11e15-b5f0-4f2c-8afa-1152ec69f3d3@ondemand.us-west-1.saucelabs.com:443/wd/hub";
		DesiredCapabilities caps = null;

		if (os.equalsIgnoreCase("Windows")) {
 
			if (browser.equalsIgnoreCase("Chrome")) {
				caps = DesiredCapabilities.chrome();
				caps.setCapability("platform", "Windows 10"); 
				caps.setCapability("version", "88.0");
				caps.setCapability("name", "MySauceTest");
				
			}

			if (browser.equalsIgnoreCase("FF")) {
				caps = DesiredCapabilities.firefox();
				caps.setCapability("platform", "Windows 7");
				caps.setCapability("version", "74.0");
				caps.setCapability("name", this.getClass().getSimpleName());
			}

			if (browser.equalsIgnoreCase("IE")) {
				caps = DesiredCapabilities.internetExplorer();
				caps.setCapability("platform", "Windows 8.1");
				caps.setCapability("version", "11.0");
				caps.setCapability("name", this.getClass().getSimpleName());
			}

		}

		if (os.equalsIgnoreCase("Mac")) {

			if (browser.equalsIgnoreCase("Chrome")) {

				caps = DesiredCapabilities.chrome();
				caps.setCapability("platform", "OS X 10.11");
				caps.setCapability("version", "48.0");
				caps.setCapability("name", this.getClass().getSimpleName());
			}

			if (browser.equalsIgnoreCase("FF")) {

				caps = DesiredCapabilities.firefox();
				caps.setCapability("platform", "OS X 10.11");
				caps.setCapability("version", "44.0");
				caps.setCapability("name", this.getClass().getSimpleName());
			}

			if (browser.equalsIgnoreCase("Safari")) {

				caps = DesiredCapabilities.safari();
				caps.setCapability("platform", "OS X 10.11");
				caps.setCapability("version", "9.0");
				caps.setCapability("name", this.getClass().getSimpleName());
			}
		}

		if (os.equalsIgnoreCase("iOS")) {

			caps = DesiredCapabilities.iphone();
			caps.setCapability("platform", "OS X 10.10");
			caps.setCapability("version", "9.2");
			caps.setCapability("deviceName", "iPhone 6");
			caps.setCapability("deviceOrientation", "portrait");
			caps.setCapability("name", this.getClass().getSimpleName());
		}

		if (os.equalsIgnoreCase("Android")) {

			caps = DesiredCapabilities.android();
			caps.setCapability("platform", "Linux");
			caps.setCapability("version", "5.1");
			caps.setCapability("deviceName", "Android Emulator");
			caps.setCapability("deviceType", "phone");
			caps.setCapability("deviceOrientation", "portrait");
			caps.setCapability("name", this.getClass().getSimpleName());
		}

		driver = new RemoteWebDriver(new URL(url), caps);

	}

	@AfterTest
	public  void closeBrowser() {
		driver.close();
	}

}
