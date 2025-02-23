package vega.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class CartPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	private WebElement getItem(String itemName) {
		return driver.findElement(By.xpath("//div[text()='" + itemName + "']"));
	}

	private WebElement getCheckoutButton() {
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='CHECKOUT']")));
		} catch (TimeoutException e) {
			throw new RuntimeException("Checkout button is not clickable.");
		}
	}

	public void proceedToCheckout() {
		getCheckoutButton().click();
	}

	public boolean isItemInCart(String itemName) {
		return driver.findElements(By.xpath("//div[text()='" + itemName + "']")).size() > 0;
	}

}
