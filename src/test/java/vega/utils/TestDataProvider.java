package vega.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider(name = "invalidCredentials")
	public static Object[][] invalidCredentials() {

		String userName = ConfigReader.getProperty("userName");
		String password = ConfigReader.getProperty("password");

		return new Object[][] {

				{ "invalid_user", password,
						"Epic sadface: Username and password do not match any user in this service" },
				{ userName, "wrong_password",
						"Epic sadface: Username and password do not match any user in this service" },
				{ "", password, "Epic sadface: Username is required" },
				{ userName, "", "Epic sadface: Password is required" },
				{ " " + userName + " ", " " + password + " ",
						"Epic sadface: Username and password do not match any user in this service" },
				{ "", "", "Epic sadface: Username and Password are required" } };
	}

}
