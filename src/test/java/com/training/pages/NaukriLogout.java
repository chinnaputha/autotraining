package com.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class for Naukri login page
 * 
 * @author Chinna.Putha
 *
 */
public class NaukriLogout {

	WebDriverWait wait;

	@FindBy(xpath = "//a[@href='https://my.naukri.com/HomePage/view']")
	private WebElement myNaukri;

	@FindBy(css = "a[title='Logout']")
	private WebElement logout;

	public void logoutNaukri(WebDriver driver) throws Exception {
		Thread.sleep(4000);

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", myNaukri);
		
		WebElement ele = driver.findElement(By.xpath("//a[@href='https://my.naukri.com/HomePage/view']"));
		Actions act = new Actions(driver);
		act.moveToElement(ele).build().perform();

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(logout));
		logout.click();
		
		Thread.sleep(4000);

		String title = driver.getTitle();
		System.out.println("page title -->" + title);

		if (!title.contains("Job Search")) {
			throw new Exception("Naukri Job search page not displayed post logout");
		}
	}
}
