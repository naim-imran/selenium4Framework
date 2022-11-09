package automation.selenium4Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {

	public static void main(String[] args) {

		String expectedProductName = "zara coat 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("naayeem@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abcd1234$");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));

		WebElement expectedProduct = products.stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(expectedProductName))
				.findFirst().get();
		expectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> productsInCart = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = productsInCart.stream()
				.anyMatch(q -> q.getText().equalsIgnoreCase(expectedProductName));
		System.out.println(match);
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "United States").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ng-star-inserted")));
		
		
		List<WebElement> countryList = driver.findElements(By.cssSelector("section.ng-star-inserted"));
		
		

		WebElement expectedCountryName = countryList.stream()
				.filter(r -> r.findElement(By.xpath("//span[contains(text(), ' United States')]")).getText().equalsIgnoreCase("United States"))
				.findFirst().get();
		expectedCountryName.click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		String flag = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(flag.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		

	}

}
