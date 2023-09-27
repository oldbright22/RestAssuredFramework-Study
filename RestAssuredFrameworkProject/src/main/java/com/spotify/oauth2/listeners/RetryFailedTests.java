package com.spotify.oauth2.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.spotify.oauth2.utility.UtilityConstants;
import com.spotify.oauth2.utility.ConfigLoader;

public class RetryFailedTests implements IRetryAnalyzer {

	private int count = 0;
	private int retries = 1;

	@Override
	public boolean retry(ITestResult result) {

		boolean value = false;
		if (ConfigLoader.getInstance().getRetryFailedTests().equalsIgnoreCase(UtilityConstants.getYes())) {
			if (count < retries) {
				count++;
				return true;
			}
		}
		return value;
	}
}
