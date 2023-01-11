package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.selenium4Framework.Reuseables;

public class LandingPage extends Reuseables {
	@FindBy(id = "userEmail")
	WebElement userNameTxtBox;

	@FindBy(id = "userPassword")
	WebElement userPasswordTxtBox;

	@FindBy(id = "login")
	WebElement loginButton;

	@FindBy(css = "[class*='flyInOut']")
	WebElement logInErrorMessage;

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public HomePage login(String userName, String password) {
		userNameTxtBox.sendKeys(userName);
		userPasswordTxtBox.sendKeys(password);
		loginButton.click();
		return new HomePage(driver);
	}

	public String logInErrorMessage() {
		waitForElementToAppearUsingWebElement(logInErrorMessage);
		return logInErrorMessage.getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

}
