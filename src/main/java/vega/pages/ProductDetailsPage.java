package vega.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;

public class ProductDetailsPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	private WebElement getRemoveFromCartButton() {
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='REMOVE']")));
		} catch (TimeoutException e) {
			throw new RuntimeException("Remove button is not clickable.");
		}
	}

	private WebElement getBackToProductsButton() {
		return driver.findElement(By.id("back-to-products"));
	}

	public void removeItemFromCart() {
		getRemoveFromCartButton().click();
	}

	public void goBackToProducts() {
		getBackToProductsButton().click();
	}
}
