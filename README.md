# VegaIT QA Assignment - Manual & Automated Testing for SauceDemo

This repository contains manual and automated tests for the SauceDemo site, created as part of the VegaIT QA assignment.

## Project Structure

### Manual Testing
The `manual_tests` folder contains two files:
- **Vega_SauceDemo_TestCases.xlsx** – An Excel file containing test cases related to user stories received in the VegaIT QA assignment.
- **Vega_SauceDemo_BUGS.pdf** – A PDF file containing bug reports from the user stories received in the VegaIT QA assignment.

### Automated Testing
Automated testing is implemented using **Selenium WebDriver**, **TestNG** framework, and **Java** programming language. 

The test suite includes:
- **Two test scenarios** assigned in the QA assignment.
- **Two additional end-to-end tests**, selected based on the highest priority.

I have ensured compliance with all mandatory requirements from the QA assignment while incorporating some of the best practices for test automation.

Most of the optional requirements have been implemented; however, some are only partially covered, and not all optional requirements from the QA assignment are included in every test.

#### Code Structure:
- **`src/main/java/vega/pages`** – Page Object Model (POM) implementation of SauceDemo site pages.
- **`src/test/java/vega/tests`** – Test cases utilizing methods defined in POM classes.
- **`pom.xml`** – Maven project configuration, including dependencies and build information.
- **`parallel_tests.xml`** – TestNG XML configuration for running tests in parallel.

### Running Tests

#### Automated Testing
1. Clone the repository.
2. Open the project in Eclipse IDE.
3. To run an individual test, right-click on the test file and select **Run As → TestNG Test**.
4. To run tests in **Headless mode**, uncomment lines **80 and 90** in the `BaseTest` class and select **Run As → TestNG Test**.
5. To execute tests in parallel, use the `parallel_tests.xml` configuration file with TestNG and select **Run As → TestNG Test**.


### Contact
For additional questions or suggestions, feel free to contact me.

