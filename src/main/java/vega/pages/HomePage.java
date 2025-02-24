package vega.pages;

import java.util.Collections;
import java.util.List;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;

public class HomePage {

	private WebDriver driver;
	private WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	// Title of the home page
	private WebElement getHomePageTitle() {
		return driver.findElement(By.className("title"));
	}

	// Menu button
	private WebElement getMenuButton() {
		return driver.findElement(By.id("react-burger-menu-btn"));
	}

	// Inventory items list
	private WebElement getInventoryList() {
		return driver.findElement(By.className("inventory_list"));
	}

	// List of all inventory items
	private List<WebElement> getAllInventoryItems() {
		return driver.findElements(By.className("inventory_item"));
	}

	// Sort dropdown menu
	private WebElement getSortDropdown() {
		return driver.findElement(By.className("product_sort_container"));
	}

	// Sidebar menu options
	private WebElement getAllItemsLink() {
		return driver.findElement(By.id("inventory_sidebar_link"));
	}

	private WebElement getAboutLink() {
		return driver.findElement(By.id("about_sidebar_link"));
	}

	private WebElement getLogoutLink() {
		return driver.findElement(By.id("logout_sidebar_link"));
	}

	private WebElement getResetAppStateLink() {
		return driver.findElement(By.id("reset_sidebar_link"));
	}

	// Close menu button
	private WebElement getCloseMenuButton() {
		return driver.findElement(By.id("react-burger-cross-btn"));
	}

	private WebElement getShoppingCartIcon() {
		return driver.findElement(By.className("shopping_cart_link"));
	}

	private WebElement getShoppingCartBadge() {
		return driver.findElement(By.className("shopping_cart_badge"));
	}

	// Dynamic elements for items
	private WebElement getAddToCartButton(String itemName) {
		try {
			WebElement button = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[text()='" + itemName + "']/following::button[1]")));
			return button;
		} catch (TimeoutException e) {
			throw new RuntimeException("Add to Cart button for " + itemName + " is not clickable.");
		}
	}

	private WebElement getItemElementPicture(String itemName) {
		try {
			WebElement image = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[text()='" + itemName + "']/preceding::img[1]")));
			return image;
		} catch (TimeoutException e) {
			throw new RuntimeException("Image for " + itemName + " is not visible.");
		}
	}

	private WebElement getRemoveFromCartButton(String itemName) {
		try {
			WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[text()='" + itemName + "']/following::button[contains(text(),'Remove')]")));
			return button;
		} catch (TimeoutException e) {
			throw new RuntimeException("Remove from Cart button for " + itemName + " is not clickable.");
		}
	}

	// Methods for Scenario 1
	public void selectSortOption(String option) {
		Select sortDropdown = new Select(getSortDropdown());
		sortDropdown.selectByVisibleText(option);
	}

	// Method that returns a list of item prices from the cart
	public List<Double> getItemPrices() {
		List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
		List<Double> prices = new ArrayList<>();

		for (int i = 0; i < priceElements.size(); i++) {
			WebElement e = priceElements.get(i);
			String priceText = e.getText().replaceAll("[^\\d.]", "").trim();
			prices.add(Double.parseDouble(priceText));
		}

		return prices;
	}

	// Method that returns a list of sorted item prices in ascending order
	public List<Double> getSortedItemPrices() {
		List<Double> prices = getItemPrices();
		List<Double> sortedPrices = new ArrayList<>(prices);
		Collections.sort(sortedPrices);
		return sortedPrices;
	}

	public boolean verifyPricesSorted() {
		return getItemPrices().equals(getSortedItemPrices());
	}

	// Methods for Scenario 2
	public void addItemToCart(String itemName) {
		getAddToCartButton(itemName).click();
	}

	public void openItemDetails(String itemName) {
		getItemElementPicture(itemName).click();
	}

	public void removeItemFromCart(String itemName) {
		getRemoveFromCartButton(itemName).click();
	}

	public int getCartItemCount() {
		String itemCountText = getShoppingCartBadge().getText();
		return Integer.parseInt(itemCountText);
	}

	// Methods for Scenario 3
	public boolean isOnProductsPage() {
		return driver.getCurrentUrl().contains("inventory.html");
	}

	public void goToCart() {
		getShoppingCartIcon().click();
	}
}
