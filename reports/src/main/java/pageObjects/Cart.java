package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.selenium4Framework.Reuseables;

public class Cart extends Reuseables {
	private WebDriver driver;

	@FindBy(css = ".cartSection h3")
	List<WebElement> allProductsInCart;

	@FindBy(css = ".totalRow button")
	WebElement checkOutButton;

	public Cart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean getProductInCartByName(String expectedProductName) {
		return allProductsInCart.stream().anyMatch(q -> q.getText().equalsIgnoreCase(expectedProductName));
	}

	public OrderPage checkOutButton() {
		checkOutButton.click();
		return new OrderPage(driver);
	}

}
