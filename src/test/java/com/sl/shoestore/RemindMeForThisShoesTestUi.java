package com.sl.shoestore;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.sl.ExtentListeners.ExtentListeners;
import com.sl.utilities.DataProviders;

/**
 * This class is to have the test cases for Set Notification for one particular shoe which is under pre-order
 * @Created	15 October 2020
 */
public class RemindMeForThisShoesTestUi extends ShoeStoreBaseTest{
	
	/**
	 * This test case to Set Notification for one particular shoe which is under pre-order
	 * @param  data   Hashtable which contains the data for to select/enter.
	 * @throws InterruptedException 
	 */
	@Test(dataProviderClass=DataProviders.class,dataProvider="shoeStoreDP")
	public void remindMeForThisShoesTest(Hashtable<String,String> data) throws InterruptedException{
		//ExtentListeners.testReport.get().info("Iteration :-->  "+data.get("Iteration"));
		openApplication(envProps.getProperty("shoeStoreAppUrl"));
		remindMeForThisShoe(data);
		validateRemindMeForThisShoesSuccessMsg(data);			
	}

		
	

}
