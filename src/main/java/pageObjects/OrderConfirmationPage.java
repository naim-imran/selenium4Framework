package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.selenium4Framework.Reuseables;

public class OrderConfirmationPage extends Reuseables {

	@FindBy(css = ".hero-primary")
	WebElement thankYouTXT;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String thankYouTXT() {
		return thankYouTXT.getText();
	}

}