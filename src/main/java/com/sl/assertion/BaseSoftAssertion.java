package com.sl.assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sl.ExtentListeners.ExtentListeners;

public class BaseSoftAssertion {
	
	protected static final String BREAK_LINE = "</br>";
    protected List<Throwable> verificationFailures = new ArrayList<Throwable>();

	public List<Throwable> getVerificationFailures() {
		return verificationFailures;
	}
	
	public void verifyAll() throws Exception{
		if (!this.getVerificationFailures().isEmpty()) {
			int size = this.getVerificationFailures().size();
            // if there's only one failure just set that
            if (size == 1) {
            	throw new Exception(((Throwable) this.getVerificationFailures().get(0)).getMessage());
            } else if(size!=0) {
                // create a failure message with all failures and stack
                // traces (except last failure)
                //StringBuffer failureMessage = new StringBuffer("Multiple validation failures (").append(size).append("):"+BREAK_LINE);
                StringBuffer failureMessage = new StringBuffer("Multiple validation failures (").append(size).append(")");
                // set merged throwable
                Throwable merged = new Throwable(failureMessage.toString());
                throw new Exception(merged.getMessage());
            }
		}
	}	
	
	/**
	 * Log exception details into Extent Report 
	 * @param ae:AssertionError
	 */	
	public void logSoftAssertionDetailsInReport(AssertionError ae){
		
		String excepionMessage1=ae.getMessage().toString();
		ExtentListeners.testReport.get().fail(excepionMessage1);
		String excepionMessage=Arrays.toString(ae.getStackTrace());
		ExtentListeners.testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Soft Assertion Failed:Expand to see details"
				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");		
	}
	
	

}
