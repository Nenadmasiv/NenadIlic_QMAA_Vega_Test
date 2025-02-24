package vega.tests;

import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import vega.pages.CartPage;
import vega.pages.CheckoutAddYourInfoPage;
import vega.pages.CheckoutOrderConfirmationPage;
import vega.pages.CheckoutSummaryPage;
import vega.pages.HomePage;
import vega.pages.LoginPage;
import vega.pages.ProductDetailsPage;
import vega.utils.ConfigReader;
import vega.utils.TestConstants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class BaseTest {

	public WebDriver driver;
	public WebDriverWait wait;
	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	public LoginPage loginPage;
	public HomePage homePage;
	public ProductDetailsPage productDetailsPage;
	public CheckoutAddYourInfoPage checkoutAddYourInfoPage;
	public CheckoutSummaryPage checkoutSummaryPage;
	public CheckoutOrderConfirmationPage checkoutOrderConfirmationPage;
	public CartPage cartPage;

	public String baseUrl;
	public String userName;
	public String password;
	public String firstName;
	public String lastName;
	public String zip;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browser) {

		baseUrl = ConfigReader.getProperty("baseUrl");
		userName = ConfigReader.getProperty("userName");
		password = ConfigReader.getProperty("password");
		firstName = TestConstants.FIRST_NAME;
		lastName = TestConstants.LAST_NAME;
		zip = TestConstants.ZIP_CODE;

		logger.info("Initializing WebDriver");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();

//            UNCOMMENT THE FOLLOWING LINE OF CODE TO RUN IN HEADLESS MODE
//            options.addArguments("--headless");

			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();

//            UNCOMMENT THE FOLLOWING LINE OF CODE TO RUN IN HEADLESS MODE
//            options.addArguments("-headless");

			options.addArguments("--incognito");
			driver = new FirefoxDriver(options);
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		productDetailsPage = new ProductDetailsPage(driver);
		cartPage = new CartPage(driver);
		checkoutAddYourInfoPage = new CheckoutAddYourInfoPage(driver);
		checkoutSummaryPage = new CheckoutSummaryPage(driver);
		checkoutOrderConfirmationPage = new CheckoutOrderConfirmationPage(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.navigate().to(baseUrl);
		logger.info("Navigated to login page");
	}

	@AfterMethod
	public void afterMethod() {

	}

	@AfterClass
	public void afterClass() {
		logger.info("Closing WebDriver");
		driver.quit();
	}

}
