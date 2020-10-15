package com.sl.assertion;

import org.testng.Assert;

import com.sl.ExtentListeners.ExtentListeners;

public class SoftAssertion extends BaseSoftAssertion{
	

	/**
	 * Asserts that a condition if true. 
	 * 
	 * @param condition:boolean
	 * @return boolean
	 */
	public boolean verifyTrue(final boolean condition) {
		return verifyTrue(condition, "");
	}
	/**
	 * Asserts that a condition is true. 
	 * @param condition:boolean
	 * @param stepDetail:String
	 * @return boolean
	 */
	public boolean verifyTrue(final boolean condition, final String stepDetail) {
		boolean result = false;
		String message = "verifyTrue: " + stepDetail + BREAK_LINE + " Actual: " + condition;
		try {
			Assert.assertTrue(condition);
			ExtentListeners.testReport.get().pass(message);
			result = true;
		} catch (AssertionError ae) {
			verificationFailures.add(ae);
			ExtentListeners.testReport.get().fail(message);
			logSoftAssertionDetailsInReport(ae);
		}		
		return result;
	}
	


}
