package com.sl.shoestore;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.sl.ExtentListeners.ExtentListeners;
import com.sl.utilities.DataProviders;


/**
 * This class is to have the test cases for Shoe Search By Brand Name
 * @Created	15 October 2020
 */
public class SearchByBrandNameTestUi extends ShoeStoreBaseTest{
	
	/**
	 * This test case to search Shoe by Brand Name and verify the result page.
	 * @param  data   Hashtable which contains the data for to select/enter.
	 */
	@Test(dataProviderClass=DataProviders.class,dataProvider="shoeStoreDP")
	public void searchShoeByBrandName(Hashtable<String,String> data){
		ExtentListeners.testReport.get().info("Iteration :-->  "+data.get("Iteration"));
		openApplication(envProps.getProperty("shoeStoreAppUrl"));
		searchByBrandName(data);
		validateShoeStoreResultPageTitle(data);	
	}

		
	

}
