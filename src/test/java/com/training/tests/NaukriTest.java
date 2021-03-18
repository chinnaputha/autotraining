package com.training.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.training.pages.NaukriLogin;
import com.training.pages.NaukriLogout;
import com.training.pages.UpdateProfile;
import com.training.utils.TestBaseUtil;

public class NaukriTest extends TestBaseUtil {

	@Test(alwaysRun = true)
	@Parameters({ "username", "password" })
	public void loginToNaukri(String username, String password) throws Exception {
		lanuchApplication("https://www.naukri.com/nlogin/login");
		NaukriLogin nl = PageFactory.initElements(driver, NaukriLogin.class);
		nl.loginNaukri(driver, username, password);
		captureScreenshot();
	}

	@Test(dependsOnMethods = "loginToNaukri")
	public void updateNaukriProfile() throws Exception {
		UpdateProfile up = PageFactory.initElements(driver, UpdateProfile.class);
		up.clickUpdateProfile(driver);
		up.editProfile(driver);

	}
	
	@Test(dependsOnMethods = "updateNaukriProfile")
	public void logoutNaukri() throws Exception {
		NaukriLogout nlogout = PageFactory.initElements(driver, NaukriLogout.class);
		nlogout.logoutNaukri(driver);

	}

}
