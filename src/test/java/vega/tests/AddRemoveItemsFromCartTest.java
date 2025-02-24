package vega.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import vega.utils.ConfigReader;

public class AddRemoveItemsFromCartTest extends BaseTest {

	@Test
	public void addAndRemoveItemFromCart() {
		loginPage.login(userName, password);

		// Add "Sauce Labs Backpack" to cart
		homePage.addItemToCart("Sauce Labs Backpack");

		// Add "Sauce Labs Onesie" to cart
		homePage.addItemToCart("Sauce Labs Onesie");

		// Open "Sauce Labs Backpack" details
		homePage.openItemDetails("Sauce Labs Backpack");

		// Remove "Sauce Labs Backpack" from cart
		productDetailsPage.removeItemFromCart();

		// Verify that the cart contains only 1 item
		Assert.assertEquals(homePage.getCartItemCount(), 1, "Cart should contain 1 item after removing the backpack.");

	}
}
