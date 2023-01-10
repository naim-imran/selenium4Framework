package testListeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	byte count = 0;
	byte maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		if (count<maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
