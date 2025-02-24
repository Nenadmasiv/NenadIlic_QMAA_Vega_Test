package vega.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class CheckoutSummaryPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public CheckoutSummaryPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	// Method that returns the total price of the items in the cart
	public double getItemTotal() {
		return Double.parseDouble(
				driver.findElement(By.className("summary_subtotal_label")).getText().replace("Item total: $", ""));
	}

	// Method that returns the tax value
	public double getTax() {
		return Double
				.parseDouble(driver.findElement(By.className("summary_tax_label")).getText().replace("Tax: $", ""));
	}

	// Method that returns the total value
	public double getTotal() {
		return Double
				.parseDouble(driver.findElement(By.className("summary_total_label")).getText().replace("Total: $", ""));
	}

	// Method that sums the prices of all the items in the cart
	public double calculateTotalPrice() {
		double total = 0.0;
		List<WebElement> itemPrices = driver.findElements(By.className("inventory_item_price"));

		for (int i = 0; i < itemPrices.size(); i++) {
			total += Double.parseDouble(itemPrices.get(i).getText().replace("$", ""));
		}

		return total;
	}

	private WebElement getCheckoutButton() {
		try {
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='FINISH']")));
		} catch (TimeoutException e) {
			throw new RuntimeException("Finish button is not clickable.");
		}
	}

	public void finishCheckout() {
		getCheckoutButton().click();
	}

	// Method that checks if the prices are correctly calculated
	public boolean verifyFinalTotal() {
		double itemTotal = getItemTotal();
		double tax = getTax();
		double total = getTotal();
		return (itemTotal + tax == total); // Direktno poređenje
	}

}
