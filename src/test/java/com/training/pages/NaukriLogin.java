package com.training.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class for Naukri login page
 * @author Chinna.Putha
 *
 */
public class NaukriLogin {
	
	WebDriverWait wait;
	
	@FindBy(css="input[id='usernameField']")
	private WebElement username;
	
	
	@FindBy(xpath ="//input[@id='passwordField']")
	private WebElement password;
	
	
	@FindBy(xpath ="//button[@type='submit'][1]")
	private WebElement loginBtn;
	
	@FindBy(xpath ="//div[contains(@class,'user-name')]")
	private WebElement profileTitle;
	
	
	
	public void loginNaukri(WebDriver driver,String usr,String pwd) {
		username.sendKeys(usr);
		password.sendKeys(pwd);
		loginBtn.click();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='updateProfile']")));
		
		String profileName = profileTitle.getText();
		System.out.println("Profile naame--->"+profileName);
		assertEquals(profileName, "TestingPurpose");
	}
}
