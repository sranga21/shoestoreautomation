package com.sl.shoestore;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.sl.ExtentListeners.ExtentListeners;
import com.sl.utilities.DataProviders;



/**
 * This class is to have the test cases Remind Me for the New Shoes functionality
 * @Created	15 October 2020
 */
public class RemindMeForNewShoesTestUi extends ShoeStoreBaseTest{
	
	/**
	 * This test case to to verify Remind Me for the New Shoes functionality
	 * @param  data   Hashtable which contains the data for to select/enter.
	 */
	@Test(dataProviderClass=DataProviders.class,dataProvider="shoeStoreDP")
	public void remindMeForNewShoesTest(Hashtable<String,String> data){
		//ExtentListeners.testReport.get().info("Iteration :-->  "+data.get("Iteration"));
		openApplication(envProps.getProperty("shoeStoreAppUrl"));
		remindMeForNewShoes(data);
		validateRemindMeForNewShoesSuccessMsg(data);			
	}

		
	

}
