package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.selenium4Framework.Reuseables;

public class HomePage extends Reuseables {

	private WebDriver driver;

	@FindBy(css = "div.mb-3")
	List<WebElement> allProductsAsList;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartBtton;

	By toastContainer = By.cssSelector("#toast-container");

	By loadAnimation = By.cssSelector(".ng-animating");

	By allProductsBylocator = By.cssSelector("div.mb-3");

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public List<WebElement> getAllProducts() {
		waitForElementToAppearUsingByLocator(allProductsBylocator);
		return allProductsAsList;
	}

	public WebElement getProductByName(String expectedProductName) {
		return getAllProducts().stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(expectedProductName))
				.findFirst().get();

	}

	public void addProductToCart(String expectedProductName) {
		getProductByName(expectedProductName).findElement(By.cssSelector(".card-body button:last-of-type")).click();
	}
	
	

	public Cart goToCart() {
		waitForElementToAppearUsingByLocator(toastContainer);
		waitForElementToDisappear(loadAnimation);
		cartBtton.click();
		return new Cart(driver);

	}

}
