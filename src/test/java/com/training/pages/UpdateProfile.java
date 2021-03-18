package com.training.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class for Naukri Profile update page
 * @author Chinna.Putha
 *
 */
public class UpdateProfile {
	
	WebDriverWait wait;
	
	@FindBy(xpath="//div[text()='UPDATE PROFILE']")
	private WebElement updateProfile;
	
	@FindBy(css="div.uploadBtn")
	private WebElement updateResume;
	
	@FindBy(xpath="//em[@class='icon edit']")
	private WebElement editProfile;
	
	@FindBy(css="button#saveBasicDetailsBtn")
	private WebElement saveBtn;
	
	
	@FindBy(xpath="//span[normalize-space()='Profile Last updated']/following-sibling::span")
	private WebElement profileStatusUpdate;
	

	/**
	 * Method to click on udpate profile button and wait for the Profile page
	 * @param driver
	 */
	public void clickUpdateProfile(WebDriver driver) {
		//updateProfile.click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", updateProfile);
		//js.executeScript("arguments[0].scrollIntoView();", ele);
		//Thread.sleep(4000);
		//javascript click
		
		
		//wait until element to be visible and clickable
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(updateResume));
		
		String pageTitle = driver.getTitle();
		System.out.println("Title of the page -->"+pageTitle);
		assertEquals(pageTitle, "Profile | Mynaukri");
	}
	
	
	/**
	 * Method to click on udpate profile button and wait for the Profile page
	 * @param driver
	 */
	public void editProfile(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView();", editProfile);
			editProfile.click();
			
			Thread.sleep(4000);
			//wait until element to be visible and clickable
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
			
			saveBtn.click();
			
			wait.until(ExpectedConditions.visibilityOf(profileStatusUpdate));
			
			String status = profileStatusUpdate.getText();
			System.out.println("Status --->"+status);
			assertEquals(status, "today");
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
}
