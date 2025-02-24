package vega.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class CheckoutAddYourInfoPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public CheckoutAddYourInfoPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	private WebElement getFirstNameInputFiel() {
		return driver.findElement(By.id("first-name"));
	}

	private WebElement getLastNameInputFiel() {
		return driver.findElement(By.id("last-name"));
	}

	private WebElement getPostalCodeInputFiel() {
		return driver.findElement(By.id("postal-code"));
	}

	private WebElement getContinueButton() {
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']")));
		} catch (TimeoutException e) {
			throw new RuntimeException("Continue button is not clickable.");
		}
	}

	public void enterCheckoutDetails(String firstName, String lastName, String zip) {
		getFirstNameInputFiel().sendKeys(firstName);
		getLastNameInputFiel().sendKeys(lastName);
		getPostalCodeInputFiel().sendKeys(zip);
		getContinueButton().click();
	}

}
