package vega.tests;

import vega.utils.ConfigReader;
import vega.utils.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardUserLoginTests extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(StandardUserLoginTests.class);

	@Test
	public void testSuccessfulLogin() {
		logger.info("Testing successful login");
		loginPage.login(userName, password);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html",
				"User is not on the inventory page");
	}

	@Test(dataProvider = "invalidCredentials", dataProviderClass = TestDataProvider.class)
	public void testInvalidLogin(String username, String password, String expectedErrorMessage) {
		logger.info("Testing login with username: " + username + " and password: " + password);
		loginPage.login(username, password);
		String actualErrorMessage = loginPage.getErrorMessageText();
		logger.debug("Error message displayed: " + actualErrorMessage);
		Assert.assertEquals(loginPage.getErrorMessageText(), expectedErrorMessage, "Error message does not match");
	}

	@Test
	public void testLoginWithEnterKey() {
		logger.info("Testing login using Enter key");
		loginPage.login(userName, password);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html",
				"User is not on the inventory page");
	}
}
