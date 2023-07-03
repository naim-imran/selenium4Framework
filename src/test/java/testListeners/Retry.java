package testListeners;

import java.util.Properties;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import automation.selenium4Framework.SetupBrowser;

public class Retry implements IRetryAnalyzer{
	
	byte count = 0;

	
	@Override
	public boolean retry(ITestResult result) {
		
		Properties prop = new SetupBrowser().getProps();
		
		
		if (count<Byte.parseByte(prop.getProperty("failedTestRetry"))) {
			count++;
			return true;
		}
		return false;
	}

}
