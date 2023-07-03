package automation.selenium4Framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Cart;
import pageObjects.HomePage;
import pageObjects.OrderConfirmationPage;
import pageObjects.OrderPage;

public class SubmitOrderTest extends SetupBrowser {
	@Test(groups = { "smoke" }, retryAnalyzer = testListeners.Retry.class )
	public void submitOrder() {

		String expectedProductName = "zara coat 3";

		HomePage homePage = getLandingPage().login("naayeem@gmail.com", "Abcd1234$");

		homePage.addProductToCart(expectedProductName);
		Cart cart = homePage.goToCart();

		OrderPage orderPage = cart.checkOutButton();

		orderPage.selectCountryTxtBox("United States");
		orderPage.selectCountryFrmDropDown("United States");
		OrderConfirmationPage confirmationPage = orderPage.placeOrderBtn();
		

		Assert.assertTrue(confirmationPage.thankYouTXT().equalsIgnoreCase("THANKYOU FOR THE ORDER.88"));

	}
	
	

}
