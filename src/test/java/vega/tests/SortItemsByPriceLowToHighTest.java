package vega.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SortItemsByPriceLowToHighTest extends BaseTest {

	@Test
	public void priceSorting() {
		loginPage.login(userName, password);

		// Sort the items by “Price (low to high)”
		homePage.selectSortOption("Price (low to high)");

		// Verify that the items are sorted from cheapest to most expensive
		Assert.assertTrue(homePage.verifyPricesSorted(), "Prices are not sorted properly (Low to High).");

	}

}
