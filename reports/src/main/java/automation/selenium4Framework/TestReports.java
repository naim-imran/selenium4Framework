package automation.selenium4Framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestReports {

	public static ExtentReports getExtentReport() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//index.html");
		extentSparkReporter.config().setReportName("Web Automation Result");
		extentSparkReporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(extentSparkReporter);
		extent.setSystemInfo("Tester", "Naim");
		
		return extent;
	}
	

	


}
