package com.sl.utilities;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class DataUtil {

	public static void checkExecution(String testSuiteName, String testCaseName, String dataRunMode, Xls_Reader excel) {

		if (!isSuiteRunnable(testSuiteName)) {

			throw new SkipException("Skipping the test : " + testCaseName + " as the Runmode of Test Suite : "
					+ testSuiteName + " is NO");

		}
		
		
		if (!isTestRunnable(testCaseName,excel)) {

			throw new SkipException("Skipping the test : " + testCaseName + " as the Runmode of Test Case : "
					+ testCaseName + " is NO");

		}
		
		
		if(dataRunMode.equalsIgnoreCase(Constants.RUNMODE_NO)){
			
			
			throw new SkipException("Skipping the test : "+testCaseName+" as the Run mode to Data set is NO");
		}

	}

	public static boolean isSuiteRunnable(String suiteName) {

		Xls_Reader excel = new Xls_Reader(Constants.SUITE_XL_PATH);
		int rows = excel.getRowCount(Constants.SUITE_SHEET);

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			String data = excel.getCellData(Constants.SUITE_SHEET, Constants.SUITENAME_COL, rowNum);

			if (data.equals(suiteName)) {

				String runmode = excel.getCellData(Constants.SUITE_SHEET, Constants.RUNMODE_COL, rowNum);
				if (runmode.equals(Constants.RUNMODE_YES))
					return true;
				else
					return false;

			}

		}

		return false;

	}

	public static boolean isTestRunnable(String testCaseName, Xls_Reader excel) {

		int rows = excel.getRowCount(Constants.TESTCASE_SHEET);

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			String data = excel.getCellData(Constants.TESTCASE_SHEET, Constants.TESTCASE_COL, rowNum);

			if (data.equals(testCaseName)) {

				String runmode = excel.getCellData(Constants.TESTCASE_SHEET, Constants.RUNMODE_COL, rowNum);
				if (runmode.equals(Constants.RUNMODE_YES))
					return true;
				else
					return false;

			}

		}

		return false;

	}

	/**
	 * This method get the data from excel for supplied test case and return two
	 * dimentation array and put data in Hashtable.
	 * 
	 * @param TestCaseName
	 *            Test Case Name
	 * 
	 * @param ExcelObject
	 *            Excel file object which holds test data
	 * 
	 * @return 2D Array of data
	 */
	public static Object[][] getData(String testName, Xls_Reader xls) {
		String sheetName = "Data";
		int testStartRowNum = 1;

		while (!xls.getCellData(sheetName, 0, testStartRowNum).equals(testName)) {
			testStartRowNum++;
		}
		System.out.println("Test starts from row: " + testStartRowNum);
		int colStartRowNum = testStartRowNum + 1;
		int dataStartRowNum = testStartRowNum + 2;

		// calculate rows of data
		int rows = 0;
		while (!xls.getCellData(sheetName, 0, dataStartRowNum + rows).equals("")) {
			rows++;
		}
		System.out.println("Total rows are: " + rows);

		// calculate total cols
		int cols = 0;
		while (!xls.getCellData(sheetName, cols, colStartRowNum).equals("")) {
			cols++;
		}
		System.out.println("Total cols are: " + cols);

		Object[][] data = new Object[rows][1];
		// Object[][] data = new Object[rows][cols];
		// read the data
		int dataRow = 0;
		Hashtable<String, String> table = null;
		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < cols; cNum++) {
				String key = xls.getCellData(sheetName, cNum, colStartRowNum);
				String value = xls.getCellData(sheetName, cNum, rNum);
				// System.out.println(xls.getCellData(sheetName, cNum, rNum));
				// data[dataRow][cNum]=xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
			}
			data[dataRow][0] = table;
			dataRow++;
		}
		return data;
	}

}
