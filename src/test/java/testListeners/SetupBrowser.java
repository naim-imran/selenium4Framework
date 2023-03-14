package testListeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class SetupBrowser {
	
	public WebDriver driver;
	private Properties properties;
	private LandingPage landingPage;

	public WebDriver initializeBrowser() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(
					System.getProperty("user.dir") + "//src//main//java//data//globalData.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(co);
			
			////////////////////////////////////////////////////
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}
	
	public static String getScreenShoot(String testCaseName, WebDriver driver) {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//"+ testCaseName + ".png");
		try {
			FileUtils.copyFile(source, file);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() {
		driver = initializeBrowser();
		driver.get(properties.getProperty("environmentURL"));
		landingPage = new LandingPage(driver);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	public LandingPage getLandingPage() {
		return landingPage;
	}
	
	

}
