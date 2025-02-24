package vega.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	private WebElement getUserNameInputField() {
		return driver.findElement(By.id("user-name"));
	}

	private WebElement getPassInputField() {
		return driver.findElement(By.id("password"));
	}

	private WebElement getLoginButton() {
		return driver.findElement(By.id("login-button"));
	}

	private WebElement getErrorMessage() {
		return driver.findElement(By.cssSelector("h3[data-test='error']"));
	}

	public void login(String username, String password) {
		getUserNameInputField().sendKeys(username);
		getPassInputField().sendKeys(password);
		getLoginButton().click();
	}

	public void clickLoginButton() {
		getLoginButton().click();
	}

	public void loginWithClear(String username, String password) {
		logger.info("Entering username: " + username);
		getUserNameInputField().click();
		getUserNameInputField().clear();
		getUserNameInputField().sendKeys(username);
		logger.info("Entering password");
		getPassInputField().click();
		getPassInputField().clear();
		getPassInputField().sendKeys(password);
		logger.info("Clicking login button");
		getLoginButton().click();
	}

	public void loginWithEnterKey(String username, String password) {
		logger.info("Entering username: " + username);
		getUserNameInputField().click();
		getUserNameInputField().clear();
		getUserNameInputField().sendKeys(username);
		logger.info("Entering password");
		getPassInputField().click();
		getPassInputField().clear();
		getPassInputField().sendKeys(password);
		logger.info("Pressing Enter key");
		getPassInputField().sendKeys(Keys.RETURN);
	}

	public String getErrorMessageText() {
		return getErrorMessage().getText();
	}

}
