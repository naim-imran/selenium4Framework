package automation.selenium4Framework;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ErrorValidationTest extends SetupBrowser{

	@Test(description = "validate the working of login module", priority = 1, groups = { "smoke" }, retryAnalyzer = testListeners.Retry.class , dataProvider = "getItemName")
	public void logInModuleNegativeTest(String email, String password) {

		getLandingPage().login(email, password);

		Assert.assertEquals("Incorrect email or password.", getLandingPage().logInErrorMessage());

	}

	@Test(priority = 0)
	public void titleTest() {
		Assert.assertEquals("Let's Shop", getLandingPage().getTitle());

	}

	@DataProvider
	public Object[][] getItemName() {
		Reuseables reuseables = new Reuseables(initializeBrowser());
		return reuseables.getTestData("loginData",
				System.getProperty("user.dir") + "//src//main//java//data//loginData.xlsx");

		// return new Object[][] { { "hjhgakhk@gmail.com", "ghjgfjkhsjk" }, {
		// "hghjkshpassword@gmail.com", "hagkh" } };0000000000000000

	}

}