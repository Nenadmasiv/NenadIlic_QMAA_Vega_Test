package vega.pages;

import org.openqa.selenium.WebDriver;

public class CheckoutOrderConfirmationPage {

	private WebDriver driver;

	public CheckoutOrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}

	// Metoda koja proverava da li je prikazana poruka za uspešnu narudžbinu
	public boolean isOrderCompleted() {
		return driver.getPageSource().contains("THANK YOU FOR YOUR ORDER");
	}
}
