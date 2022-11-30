package automation.selenium4Framework;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import pageObjects.Cart;
import pageObjects.HomePage;
import pageObjects.OrderConfirmationPage;
import pageObjects.OrderPage;
import testListeners.SetupBrowser;

public class SubmitOrderTest extends SetupBrowser {
	@Test(groups = { "smoke" })
	public void submitOrder() {

		String expectedProductName = "zara coat 3";

		HomePage homePage = getLandingPage().login("naayeem@gmail.com", "Abcd1234$");

		homePage.addProductToCart(expectedProductName);
		Cart cart = homePage.goToCart();

		OrderPage orderPage = cart.checkOutButton();

		orderPage.selectCountryTxtBox("United States");
		orderPage.selectCountryFrmDropDown("United States");
		OrderConfirmationPage confirmationPage = orderPage.placeOrderBtn();

		AssertJUnit.assertTrue(confirmationPage.thankYouTXT().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	

}
