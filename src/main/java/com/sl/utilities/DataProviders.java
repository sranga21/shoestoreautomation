package com.sl.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {	
	
	
	@DataProvider(name="coreDP",parallel=true)
	public static Object[][] getDataSuite(Method m) {		
		Xls_Reader excel = new Xls_Reader(Constants.CORE_DATA_XLS_PATH);
		String testcase = m.getName();
		return DataUtil.getData(testcase, excel);
	}
	
	@DataProvider(name="shoeStoreDP",parallel=true)
	public static Object[][] getDataShoeStoreSuite(Method m) {		
		Xls_Reader excel = new Xls_Reader(Constants.SHOESTORE_DATA_XLS_PATH);
		String testcase = m.getName();
		return DataUtil.getData(testcase, excel);
	}
	
	@DataProvider(name="timeCardDP",parallel=true)
	public static Object[][] getDataTimeCardSuite(Method m) {		
		Xls_Reader excel = new Xls_Reader(Constants.TIMECARD_DATA_XLS_PATH);
		String testcase = m.getName();
		return DataUtil.getData(testcase, excel);
	}
	


}
