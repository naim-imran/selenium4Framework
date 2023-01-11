package automation.selenium4Framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reuseables {

	private WebDriver driver;

	public Reuseables(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToAppearUsingByLocator(By findBy) {
		WebDriverWait ExplicitlyWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ExplicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToAppearUsingWebElement(WebElement webElement) {
		WebDriverWait ExplicitlyWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ExplicitlyWait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitForElementToDisappear(By findBy) {
		WebDriverWait ExplicitlyWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ExplicitlyWait.until(ExpectedConditions.invisibilityOf(driver.findElement(findBy)));
	}

	private FileInputStream fs;
	private Workbook workBook;
	private Sheet workSheet;

	public Object[][] getTestData(String sheetName, String excelFilePath) {
		try {
			fs = new FileInputStream(excelFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workBook = WorkbookFactory.create(fs);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		workSheet = workBook.getSheet(sheetName);
		Object[][] data = new Object[workSheet.getLastRowNum()][workSheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < workSheet.getLastRowNum(); i++) {
			for (int j = 0; j < workSheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = workSheet.getRow(i + 1).getCell(j).toString();
			}
		}
		return data;
	}
	


}
