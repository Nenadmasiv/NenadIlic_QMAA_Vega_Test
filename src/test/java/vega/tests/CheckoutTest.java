package vega.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

	@Test
	public void Checkout() {
		loginPage.login(userName, password);

		// Verify that the user is on the Products page
		Assert.assertTrue(homePage.isOnProductsPage(), "Not on Products Page");

		// Add items to the shopping cart
		homePage.addItemToCart("Sauce Labs Bike Light");
		homePage.addItemToCart("Sauce Labs Fleece Jacket");
		homePage.goToCart();

		// Verify that the items are in the cart
		Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Bike Light"), "Bike Light not in cart");
		Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Fleece Jacket"), "Fleece Jacket not in cart");

		// Proceed to checkout page
		cartPage.proceedToCheckout();

		// Enter user details and continue
		checkoutAddYourInfoPage.enterCheckoutDetails(firstName, lastName, zip);

		double calculatedTotal = checkoutSummaryPage.calculateTotalPrice();
		double expectedTotal = checkoutSummaryPage.getItemTotal();

		// Compare the calculated price with the one displayed on the page
		Assert.assertEquals(calculatedTotal, expectedTotal,
				"Calculated total price does not match the displayed item total.");

		// Verification that prices including tax are calculated correctly
		Assert.assertTrue(checkoutSummaryPage.verifyFinalTotal(),
				"Final total is incorrect: Item total + Tax does not equal Total.");

		// Finish the checkout process
		checkoutSummaryPage.finishCheckout();

		// Verify that the order is successfully completed
		Assert.assertTrue(checkoutOrderConfirmationPage.isOrderCompleted(), "Order not completed");
	}

}
