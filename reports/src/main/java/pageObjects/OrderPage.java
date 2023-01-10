package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.selenium4Framework.Reuseables;

public class OrderPage extends Reuseables {

	private WebDriver driver;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountryTxtBox;

	@FindBy(css = "section.ng-star-inserted")
	List<WebElement> countryList;

	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;

	By countryDropList = By.cssSelector("section.ng-star-inserted");

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectCountryTxtBox(String countryName) {

		Actions action = new Actions(driver);
		action.sendKeys(selectCountryTxtBox, countryName).build().perform();
	}

	public void selectCountryFrmDropDown(String countryName) {
		waitForElementToAppearUsingByLocator(countryDropList);
		WebElement expectedCountryName = countryList.stream()
				.filter(r -> r.findElement(By.xpath("//span[contains(text(), ' United States')]")).getText()
						.equalsIgnoreCase(countryName))
				.findFirst().get();
		expectedCountryName.click();

	}

	public OrderConfirmationPage placeOrderBtn() {
		placeOrderBtn.click();
		return new OrderConfirmationPage(driver);
	}

}
